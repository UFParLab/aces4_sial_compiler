//TODO  clean up junk left over from aces3

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.collect.HashBiMap;

import sial.code_gen.AcesHacks;
import sial.code_gen.IndexTable.Entry;
import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.SialParsersym;

/* optable_entry_t:   type of optable entries 
typedef struct {
	f_int opcode; //1
	f_int op1_array; //2
	f_int op2_array; //3
	f_int result_array; //4
	f_int ind[mx_array_index]; //5,6,7,8,..
	//    f_int ind1
	//    f_int ind2
	//    f_int ind3
	//    f_int ind4
	union {
		f_int user_sub; //5 + mx_array_index
		f_int pardo_lock_index;
	};
	f_int instr_timer;
	f_int opblkndx;
	f_int opblock;
	f_int oploop;
	union {
		f_int scalar_op1;
		f_int pardo_chunk_size;
	};
	union {
		f_int scalar_op2;
		f_int pardo_batch_end;
	};
	union {
		f_int scalar_result;
		f_int pardo_next_batch_start;
	};
	f_int pardo_batch;
	f_int pardo_max_batch;
	f_int pardo_signal;
	f_int server_stat_key;
	f_int lineno;
//	f_int get_counter;
//	f_int put_counter;
//	f_int putinc_counter;
//	f_int prepare_counter;
//	f_int preparesum_counter;
//	f_int request_counter;
//	f_int pardomsg_counter;
} optable_entry_t;
*/


public class OpTable {
	public static class Entry{
		int opcode; //1
		int op1_array; //2
		int op2_array; //3
		int result_array; //4
		int[]  ind; //size is max_array_index
//		int user_sub; //also pardo_lock_index;
//		int instr_timer;
//		int opblkndx;
//		int opblock;
//		int oploop;
//		int scalar_op1;  //also int pardo_chunk_size;
//		int scalar_op2;  //also int pardo_batch_end;
//		int scalar_result; //also int pardo_next_batch_start;
//		int pardo_batch;
//		int pardo_max_batch;
//		int pardo_signal;
//		int server_stat_key;
		int lineno;
//		int get_counter;
//		int put_counter;
//		int putinc_counter;
//		int prepare_counter;
//		int preparesum_counter;
//		int request_counter;
//		int pardomsg_counter;
		
		//allocate, deallocate, create, delete
//		Entry(int opcode, int op1_array, int[] indices, int lineno){
//			this.opcode = opcode;
//			this.op1_array = op1_array;
//			//Array.copyOf pads array with zeros if indices.length < max_array_index
//			ind = Arrays.copyOf(indices, AcesHacks.max_array_index);
//			this.lineno = lineno;
//		}
		
//		Entry(int opcode, int op1_array, int op2_array, int result_array,
//				int[] indices, int lineno){
//			this.opcode = opcode;
//			this.op1_array = op1_array;
//			this.op2_array = op2_array;
//			this.result_array = result_array;
//			ind = Arrays.copyOf(indices, AcesHacks.max_array_index);
//			this.lineno = lineno;	
//		}
		
//		//execute instruction
//		Entry(int opcode, int user_sub, int lineno){
//			this.opcode = opcode;
//			this.user_sub = user_sub;
//			this.lineno = lineno;
//			ind = new int[AcesHacks.max_array_index];
//		}
		//execute instruction with one argument
		Entry(int opcode, int result_array, int[] ind, int user_sub, int lineno){
			this.opcode = opcode;
			this.result_array = result_array;
			this.ind = ind;
//			this.user_sub = user_sub;
			this.lineno = lineno;
		}
		

		public boolean equalVals(Object other){
			if (this == other) return true;
			if (!(other instanceof Entry)) return false;
			Entry o = (Entry)other;
			return  opcode ==o.opcode &&//1
					op1_array == o.op1_array &&//2
					op2_array == o.op2_array && //3
					result_array==o.result_array && //4
			Arrays.equals(ind, o.ind) //&& //size is max_array_index
//			user_sub== o.user_sub //&& //also pardo_lock_index&&
//			instr_timer== o.instr_timer &&
//			opblkndx== o.opblkndx &&
//			opblock== o.opblock  &&
//			oploop == o.oploop &&
//			scalar_op1 == o.scalar_op1&&  //also int pardo_chunk_size;
//			scalar_op2 ==  o.scalar_op2 &&  //also int pardo_batch_end;
//			scalar_result == o.scalar_result && //also int pardo_next_batch_start;
//			pardo_batch == o.pardo_batch &&
//			pardo_max_batch== o.pardo_max_batch &&
//			pardo_signal== o.pardo_signal &&
//			server_stat_key== o.server_stat_key 
//			&&
//			//lineno== o.lineno &&    don't compare line numbers
//			get_counter== o.get_counter &&
//			put_counter== o.put_counter &&
//			putinc_counter== o.putinc_counter &&
//			prepare_counter== o.prepare_counter &&
//			prepare_counter== o.preparesum_counter &&
//			request_counter== o.request_counter &&
//			pardomsg_counter== o.pardomsg_counter
			;
		}
		//go_to_op
		Entry(int opcode, int result_array, int[] ind, int lineno){
			this.opcode = opcode;
			this.result_array = result_array;
			assert ind.length == AcesHacks.max_rank;
			this.ind = ind; 
			this.lineno = lineno;
		}
		
		//assign_op
		Entry(int opcode, int operand_array, int result_array,  int[] ind, int lineno){
			this.opcode = opcode;
			this.op1_array = operand_array;
			this.op2_array = -1;
			this.result_array = result_array;
			this.ind = ind;
			this.lineno = lineno;
		}
		public Entry() {
		}

		public Entry(int opcode, int op1_array, int op2_array, int result_array, int[] ind,
				int lineno) {
			this.opcode = opcode;
			this.op1_array = op1_array;
			this.op2_array = op2_array;
			this.result_array = result_array;
			this.ind = ind;
			this.lineno = lineno;
		}

//		//execute with 3 args 
//		public Entry(int opcode, int op1_array, int op2_array, int result_array, int[] ind, 
//				int user_sub, int lineno) {
//			this.opcode = opcode;
//			this.op1_array = op1_array;
//			this.op2_array = op2_array;
//			this.result_array = result_array;
//			this.ind = ind;
////			this.user_sub= user_sub;
//			this.lineno = lineno;
//		}
		void read(DataInput input) throws IOException{
			opcode = input.readInt(); //1
			op1_array = input.readInt(); //2
			op2_array = input.readInt(); //3
			result_array = input.readInt(); //4
			ind = new int[AcesHacks.max_rank]; //size is max_array_index
			for (int i = 0; i < AcesHacks.max_rank; i++){
				ind[i] = input.readInt();
			}
//			user_sub = input.readInt(); //also pardo_lock_index;
			//instr_timer = input.readInt();
			//opblkndx = input.readInt();
			//opblock = input.readInt();
			//oploop = input.readInt();
			//scalar_op1 = input.readInt();  //also pardo_chunk_size;
			//scalar_op2 = input.readInt();  //also pardo_batch_end;
			//scalar_result = input.readInt(); //also pardo_next_batch_start;
			//pardo_batch = input.readInt();
			//pardo_max_batch = input.readInt();
			//pardo_signal = input.readInt();
			//server_stat_key = input.readInt();
			lineno = input.readInt();     
//			get_counter = input.readInt();
//			put_counter = input.readInt();
//			putinc_counter = input.readInt();
//			prepare_counter = input.readInt();
//			preparesum_counter = input.readInt();
//			request_counter = input.readInt();
//			pardomsg_counter = input.readInt();
		}
//		void readExpanded(DataInput input) throws IOException{
//			opcode = input.readInt(); //1
//			op1_array = input.readInt(); //2
//			op2_array = input.readInt(); //3
//			result_array = input.readInt(); //4
//			ind = new int[AcesHacks.max_array_index]; //size is max_array_index
//			for (int i = 0; i < AcesHacks.max_array_index; i++){
//				ind[i] = input.readInt();
//			}
//			user_sub = input.readInt(); //also pardo_lock_index;
//			instr_timer = input.readInt();
//			opblkndx = input.readInt();
//			opblock = input.readInt();
//			oploop = input.readInt();
//			scalar_op1 = input.readInt();  //also pardo_chunk_size;
//			scalar_op2 = input.readInt();  //also pardo_batch_end;
//			scalar_result = input.readInt(); //also pardo_next_batch_start;
//			pardo_batch = input.readInt();
//			pardo_max_batch = input.readInt();
//			pardo_signal = input.readInt();
//			server_stat_key = input.readInt();
//			lineno = input.readInt();     
////			get_counter = input.readInt();
////			put_counter = input.readInt();
////			putinc_counter = input.readInt();
////			prepare_counter = input.readInt();
////			preparesum_counter = input.readInt();
////			request_counter = input.readInt();
////			pardomsg_counter = input.readInt();
//		}
		
		static Entry readEntry(DataInput input) throws IOException{
			Entry entry = new Entry();
			entry.read(input);
			return entry;
		}
		
//		static Entry readExpandedEntry(DataInput input) throws IOException{
//			Entry entry = new Entry();
//			entry.readExpanded(input);
//			return entry;
//		}
		void write(DataOutput output) throws IOException{
			output.writeInt(opcode); //1
			output.writeInt(op1_array); //2
			output.writeInt(op2_array); //3
			output.writeInt(result_array); //4
			for (int i = 0; i < AcesHacks.max_rank; i++){  //This approach requires the size of this to be fixed, and for the sip to read individual ints
				                                                   //instead of reading an array
				output.writeInt(ind[i]);
			}
//			output.writeInt(user_sub); //also pardo_lock_index;
			output.writeInt(lineno);
		}
		
//		void writeExpanded(DataOutput output) throws IOException{
//			output.writeInt(opcode); //1
//			output.writeInt(op1_array); //2
//			output.writeInt(op2_array); //3
//			output.writeInt(result_array); //4
//			for (int i = 0; i < AcesHacks.max_array_index; i++){
//				output.writeInt(ind[i]);
//			}
//			output.writeInt(user_sub); //also pardo_lock_index;
//			output.writeInt(instr_timer);
//			output.writeInt(opblkndx);
//			output.writeInt(opblock);
//			output.writeInt(oploop);
//			output.writeInt(scalar_op1);  //also pardo_chunk_size;
//			output.writeInt(scalar_op2);  //also pardo_batch_end;
//			output.writeInt(scalar_result); //also pardo_next_batch_start;
//			output.writeInt(pardo_batch);
//			output.writeInt(pardo_max_batch);
//			output.writeInt(pardo_signal);
//			output.writeInt(server_stat_key);
//			output.writeInt(lineno);
////			output.writeInt(get_counter);
////			output.writeInt(put_counter);
////			output.writeInt(putinc_counter);
////			output.writeInt(prepare_counter);
////			output.writeInt(preparesum_counter);
////			output.writeInt(request_counter);
////			output.writeInt(pardomsg_counter);
//		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(opcode); sb.append(',');//1  
			sb.append(op1_array); sb.append(',');//2
			sb.append(op2_array); sb.append(',');//3
			sb.append(result_array); sb.append(',');//4
			sb.append('[');
			sb.append(ind[0]);
			for (int i = 1; i < AcesHacks.max_rank; i++){
				sb.append(','); sb.append(ind[i]);
			}
			sb.append(']'); sb.append(',');
//			sb.append(user_sub); sb.append(',');//also pardo_lock_index;
			//sb.append(instr_timer);sb.append(',');
			//sb.append(opblkndx);sb.append(',');
			//sb.append(opblock);sb.append(',');
			//sb.append(oploop); sb.append(',');
			//sb.append(scalar_op1);  sb.append(',');//also pardo_chunk_size;
			//sb.append(scalar_op2);  sb.append(',');//also pardo_batch_end;
			//sb.append(scalar_result); sb.append(',');//also pardo_next_batch_start;
			//sb.append(pardo_batch); sb.append(',');
			//sb.append(pardo_max_batch); sb.append(',');
			//sb.append(pardo_signal); sb.append(',');
			//sb.append(server_stat_key); sb.append(',');
			sb.append(lineno); sb.append(',');
//			sb.append(get_counter); sb.append(',');
//			sb.append(put_counter); sb.append(',');
//			sb.append(putinc_counter); sb.append(',');
//			sb.append(prepare_counter); sb.append(',');
//			sb.append(preparesum_counter); sb.append(',');
//			sb.append(request_counter); sb.append(',');
//			sb.append(pardomsg_counter); 
			return sb.toString();
		}
		
	


//	public String toStringExpanded(){
//		StringBuilder sb = new StringBuilder();
//		sb.append(opcode); sb.append(',');//1  
//		sb.append(op1_array); sb.append(',');//2
//		sb.append(op2_array); sb.append(',');//3
//		sb.append(result_array); sb.append(',');//4
//		sb.append('[');
//		sb.append(ind[0]);
//		for (int i = 1; i < AcesHacks.max_array_index; i++){
//			sb.append(','); sb.append(ind[i]);
//		}
//		sb.append(']'); sb.append(',');
//		sb.append(user_sub); sb.append(',');//also pardo_lock_index;
//		sb.append(instr_timer);sb.append(',');
//		sb.append(opblkndx);sb.append(',');
//		sb.append(opblock);sb.append(',');
//		sb.append(oploop); sb.append(',');
//		sb.append(scalar_op1);  sb.append(',');//also pardo_chunk_size;
//		sb.append(scalar_op2);  sb.append(',');//also pardo_batch_end;
//		sb.append(scalar_result); sb.append(',');//also pardo_next_batch_start;
//		sb.append(pardo_batch); sb.append(',');
//		sb.append(pardo_max_batch); sb.append(',');
//		sb.append(pardo_signal); sb.append(',');
//		sb.append(server_stat_key); sb.append(',');
//		sb.append(lineno); sb.append(',');
////		sb.append(get_counter); sb.append(',');
////		sb.append(put_counter); sb.append(',');
////		sb.append(putinc_counter); sb.append(',');
////		sb.append(prepare_counter); sb.append(',');
////		sb.append(preparesum_counter); sb.append(',');
////		sb.append(request_counter); sb.append(',');
////		sb.append(pardomsg_counter); 
//		return sb.toString();
//	}
	
}//class Entry	
	
  	 HashBiMap<String,Integer> procBiMap;  //maps procedure names optable index
   	 ArrayList<Entry> entries;
   	 int nOps;
   	 
	OpTable(){
		procBiMap = HashBiMap.create();
		entries = new ArrayList<Entry>();
		nOps = 0; 	 
	}

//	public int addOptableEntryRawIndices(int type, int specialInstructionTableIndex, int lineno){
//		int index  = nOps++;
//		entries.add(new Entry(type, specialInstructionTableIndex, lineno));
//		return index;
//	}
//	//this version adds 1 to the array table index, but the argument
//	//indices are not adjusted
//	public int addOptableEntryRawIndices(int type, int arrayTableIndex, 
//		 int[] indexarray, int lineno) {
//		 int index = nOps++;
//		 entries.add(new Entry(type,arrayTableIndex, indexarray, lineno));
//		 return index;
//	}
//	

//In ACES3, most of these routines, added 1 to anything that represented an index into a table.
//In ACES4, only values visible in sial will be indexed from 1 (index values, but 
	//not indices in the index array

	//go_to_op, reeindex_op, get_op, print_op
	//ACES4  go_to_op:  do not increment result_array for go_to_op or print_op
	public int addOptableEntry(int opcode, int result_array, int[] ind, int lineno) {
		 int index = nOps++;
		 assert ind.length <= AcesHacks.max_rank;
		 entries.add(new Entry(opcode, result_array, ind, lineno));
		 return index;
	}


	
	//assign_op:  assignment with unary rhs, fl-load-value_op
	//ACES4 do not increment operandIdex or resultIndex.  ind is not used.
	//ACES4 also with reindex_op: op_code, rank, array_table_slot, index_selector, linno
	public int addOptableEntry(int op_code, int operandIndex, int resultIndex, int[] ind, 
			 int lineno){
		int index = nOps++;
		assert ind.length <= AcesHacks.max_rank;
		entries.add(new Entry(op_code, operandIndex, resultIndex, ind, lineno));
//		entries.add(new Entry(op_code, operandIndex+1, resultIndex+1, ind, lineno));
		return index;
	}
	
	//sum_op:  assignment with binary rhs
	//ACES4 do not increment ops
	public int addOptableEntry(int opcode, int operand1, int operand2,
			int resultIndex, int[] ind, int lineno) {
		int index = nOps++;
		assert ind.length <= AcesHacks.max_rank;
//		entries.add(new Entry(opcode, operand1+1, operand2 + 1, resultIndex+1, ind, lineno));
		entries.add(new Entry(opcode, operand1, operand2, resultIndex, ind, lineno));
		return index;	
	}
	
	//do_op, enddo_op, exit_op
	public int addOptableEntry(int opcode, int[] ind, int lineno) {
		int index = nOps++;
		assert ind.length <= AcesHacks.max_rank;
		entries.add(new Entry(opcode, 0, 0, 0, ind, lineno));
		return index;
	}
	
//	//execute instruction
//	public int addOptableEntry(int opcode, int user_sub, int lineno){
//		int index = nOps++;
//		entries.add(new Entry(opcode, user_sub+1, lineno));
//		return index;
//	}
	
	//execute instruction
	//ACES4  TODO  check this!  
	//I'm not sure when it is used for an execute instruction
	//result_array and user_sub start at zero, do not increment
	public int addOptableEntry(int opcode, int result_array, int[] ind, int user_sub, int lineno){
		int index = nOps++;
		entries.add(new Entry(opcode, result_array, ind, user_sub, lineno));
		return index;
	}
	
//	//execute instruction
//	//ACES4 do not increment op1Index, op22Index, resultIndex, or functionAddr
//	public int addOptableEntry(int opcode, int op1Index, int op2Index,
//			int resultIndex, int[] ind, int functionAddr, int lineno) {
//		int index = nOps++;
//		entries.add(new Entry(opcode, op1Index, op2Index, resultIndex, ind, functionAddr, lineno));
//		return index;
//		
//	}
	
	public int addOptableEntry(Entry e){
		int index = nOps++;
		entries.add(e);
		return index;
	}
//	
//	public int addOptableEntry(int type, int op1ArrayIndex, int op2ArrayIndex, 
//			   int resultArray, int[] indices, int lineno){
//		int index= nOps++;
//		for (int j=0; j!=indices.length; j++){indices[j]++;}
//		entries.add(new Entry(type,op1ArrayIndex, op2ArrayIndex, resultArray,
//				indices, lineno));
//		return  index;
//	}
	
	public void read(int nentries, DataInput input) throws IOException{
			for (int i = 0; i < nentries; i++){
				   Entry entry = Entry.readEntry(input);
				   entries.add(entry);
				   nOps++;
				}
		}
	
//	public static OpTable readOpTable(int nentries, DataInput input) throws IOException{
//		OpTable table = new OpTable();
//		table.read(nentries, input);
//        return table;
//	}
	
	// returns a string representation of the op table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nOps);
		sb.append('\n');
		for (int i = 0; i != nOps; i++) {
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	public void write(DataOutput output) throws IOException {
		assert nOps == entries.size();
		output.writeInt(nOps);
		for (int i = 0; i != nOps; i++)
			entries.get(i).write(output);	
//		System.out.println(this);
	}


	public boolean equalVals(Object other){
		if (this == other) return true;
		if ( !(other instanceof OpTable))return false;
		OpTable o = (OpTable)other;
		for (int i = 0; i != entries.size(); i++){
			if (i >= o.entries.size()){ 
				return false;
			}
			boolean isEqual = entries.get(i).equalVals(o.entries.get(i));
			if (!isEqual){
				return false;
			}
	
		}
		return true;
	}
	
	//backpatch jump instruction if If statement.  
	// Convert jump=to addressto fortran
	//ACES4  do not convert address to fortran
	public void backpatchBranch(int instructionToChange){
//		entries.get(instructionToChange).result_array = nOps + 1;
		entries.get(instructionToChange).result_array = nOps;
	}
	
    boolean firstOpInitialized = false;

	public boolean isFirstOpInitialized() {
		return firstOpInitialized;
	}

	public void setFirstOpInitialized(boolean firstOpInitialized) {
		this.firstOpInitialized = firstOpInitialized;
	}

	public sial.code_gen.OpTable.Entry getEntryAt(int i) {
		return entries.get(i);
	}


		public static OpTable readOpTable(DataInput input) throws IOException{
			OpTable table = new OpTable();
			int numOps = input.readInt();
			for (int i = 0; i < numOps; i++){
				   Entry entry = Entry.readEntry(input);
				   table.addOptableEntry(entry);
				}
	        return table;
	}






    

//	public void backpatchDoOp(int addr, int startIndex) {
//		entries.get(addr).ind[0] = startIndex + 1;	
//	}


}
