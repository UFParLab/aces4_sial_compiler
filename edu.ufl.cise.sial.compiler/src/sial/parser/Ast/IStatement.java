package sial.parser.Ast;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>CallStatement
 *<li>ReturnStatement
 *<li>ServerBarrierStatement
 *<li>SipBarrierStatement
 *<li>DoStatement
 *<li>DoStatementSubIndex
 *<li>PardoStatement
 *<li>Section
 *<li>ExitStatement
 *<li>CycleStatement
 *<li>IfStatement
 *<li>IfElseStatement
 *<li>AllocateStatement
 *<li>DeallocateStatement
 *<li>ContiguousAllocateStatement
 *<li>ContiguousDeallocateStatement
 *<li>CreateStatement
 *<li>DeleteStatement
 *<li>PutStatement
 *<li>GetStatement
 *<li>PrepareStatement
 *<li>RequestStatement
 *<li>PrequestStatement
 *<li>CollectiveStatement
 *<li>DestroyStatement
 *<li>PrintlnStatement
 *<li>PrintStatement
 *<li>PrintIndexStatement
 *<li>PrintScalarStatement
 *<li>PrintIntStatement
 *<li>ExecuteStatement
 *<li>AssignToIdent
 *<li>AssignToBlock
 *<li>GPUSection
 *<li>GPUAllocateBlock
 *<li>GPUFreeBlock
 *<li>GPUPutBlock
 *<li>GPUGetBlock
 *<li>SetPersistent
 *<li>RestorePersistent
 *<li>AssertSame
 *</ul>
 *</b>
 */
public interface IStatement extends IASTNodeToken {}


