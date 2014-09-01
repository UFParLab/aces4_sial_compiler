package sial.parser.Ast;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>CallStatement
 *<li>ReturnStatement
 *<li>StopStatement
 *<li>ServerBarrierStatement
 *<li>SipBarrierStatement
 *<li>DoStatement
 *<li>DoStatementSubIndex
 *<li>PardoStatement
 *<li>Section
 *<li>ExitStatement
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
 *<li>CollectiveStatement
 *<li>DestroyStatement
 *<li>PrintStatement
 *<li>PrintlnStatement
 *<li>ExecuteStatement
 *<li>AssignToIdent
 *<li>AssignToBlock
 *<li>AssignToContigousDataBlock
 *<li>GPUSection
 *<li>GPUAllocate
 *<li>GPUFree
 *<li>GPUPut
 *<li>GPUGet
 *<li>SetPersistent
 *<li>RestorePersistent
 *<li>AssertSame
 *<li>BroadcastStatic
 *</ul>
 *</b>
 */
public interface IStatement extends IASTNodeToken {}


