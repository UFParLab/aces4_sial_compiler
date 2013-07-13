package sial.parser.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lpg.runtime.IToken;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sial.parser.SialParser;
import sial.parser.Ast.ASTNode;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.IdentRangeVal;
import sial.parser.Ast.IntLitRangeVal;
import sial.parser.Ast.Range;
import sial.parser.context.ASTUtils;

/**
 * This class provides static methods that check range bounds using knowledge of
 * the ACES symbolic constants.
 */


public class AcesRangeChecks {
	static Set<String> nodes;
	static SetMultimap<String, String> gt_edges;
	static SetMultimap<String, String> ge_edges;

	static {
		nodes = new HashSet<String>();
		gt_edges = HashMultimap.create();
		ge_edges = HashMultimap.create();
		String[] geOne = { "baocc", "bbocc", "eocc", "eaocc", "ebocc", "bvirt",
				"bavirt", "bbvirt", "evirt", "eavirt", "ebvirt" };
		String[] geZero = { "nocc", "naocc", "nbocc", "nvirt", "navirt",
				"nbvirt" };
		nodes.add("norb");
		nodes.addAll(Arrays.asList(geOne));
		nodes.addAll(Arrays.asList(geZero));
		// no spin MO constants
		ge_edges.put("eocc", "bocc");
		gt_edges.put("bvirt", "eocc");
		gt_edges.put("bvirt", "bocc");
		ge_edges.put("evirt", "bvirt");
		gt_edges.put("evirt", "eocc");
		gt_edges.put("evirt", "bocc");
		// alpha MO constants
		ge_edges.put("eaocc", "baocc");
		gt_edges.put("bavirt", "eaocc");
		gt_edges.put("bavirt", "baocc");
		ge_edges.put("eavirt", "bavirt");
		gt_edges.put("eavirt", "eaocc");
		gt_edges.put("eavirt", "baocc");
		// beta MO constants
		ge_edges.put("ebocc", "bbocc");
		gt_edges.put("bbvirt", "ebocc");
		gt_edges.put("bbvirt", "bbocc"); // this was not in the docs, double
											// check
		ge_edges.put("ebvirt", "bbvirt");
		gt_edges.put("ebvirt", "ebocc");
		gt_edges.put("ebvirt", "bbocc");
	}

	static boolean gt(String c0, String c1) {
		if (c0.equals(c1))
			return false;
		if (c0.equals("norb"))
			return true;
		Set<String> children = gt_edges.get(c0);
		if (children.contains(c1))
			return true;
		for (String child : children) {
			if (gt(child, c1))
				return true;
		}
		return false;
	}

	static boolean ge(String c0, String c1) {
		if (c0.equals(c1))
			return true;
		if (c0.equals("norb"))
			return true;
		Set<String> gt_children = gt_edges.get(c0);
		Set<String> ge_children = ge_edges.get(c0);
		if (gt_children.contains(c1))
			return true;
		if (ge_children.contains(c1))
			return true;
		for (String child : gt_children) {
			if (ge(child, c1))
				return true;
		}
		for (String child : ge_children) {
			if (ge(child, c1))
				return true;
		}
		return false;
	}

	// returns null if no errors, or message if error
	// only compares two compatible values. Checking for at least 1 done when
	// IntLitRangeVal is visited
	static boolean isNotInvalid(Range n) {
		IRangeVal start = n.getRangeValStart();
		IRangeVal end = n.getRangeValEnd();
		// start and end are literals
		if (start instanceof IntLitRangeVal && end instanceof IntLitRangeVal) {
			int startVal = ASTUtils.getIntVal(start);
			int endVal = ASTUtils.getIntVal(end);
			return (startVal <= endVal);
		}
		// start and end are symbolic constants
		if (start instanceof IdentRangeVal && end instanceof IdentRangeVal) {
			String startConst = ((IdentRangeVal) start).getName();
			String endConst = ((IdentRangeVal) end).getName();
			return !(gt(startConst, endConst));
		}
		return true;
	}
}