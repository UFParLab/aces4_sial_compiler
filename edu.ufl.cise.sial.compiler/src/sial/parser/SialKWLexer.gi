--
-- The Java KeyWord Lexer
--
%options package=sial.imp.parser
%options template=KeywordTemplateF.gi

%Include
--use KWLexerFoldedCaseMap for case insenstitive keywords
    KWLexerFoldedCaseMapF.gi
%End

%Export
   
--Keywords
sial
endsial
proc
endproc
return
call
pardo
endpardo
--also
do
in
enddo
cycle
exit
if
else
endif
put
get
prepare
request
prequest
collective
execute
aoindex
moindex
moaindex
mobindex 	
index
laindex
subindex
of
scalar
int
static
temp
local
distributed
served
create
delete
allocate
deallocate
--compute_integrals
destroy
where
import
predefined
special
server_barrier
sip_barrier
--constant 
--configure --reserved keyword
section
endsection
print
println
print_index
print_scalar
print_int
--sip_consistent
--scoped_extent
contiguous
--auto_allocate
gpu_on
gpu_off
gpu_allocate
gpu_free
gpu_put
gpu_get
set_persistent
restore_persistent
sparse
assert_same

%End

%Terminals
    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    -- A    B    C    D    E    F    G    H    I    J    K    L    M
    -- N    O    P    Q    R    S    T    U    V    W    X    Y    Z
    _
%End

%Start
    Keyword
%End

%Rules


    Keyword ::=   s i a l			/.$setResult($_sial);./
		| e n d s i a l		    	/.$setResult($_endsial);./
		| p r o c			/.$setResult($_proc);./
		| e n d p r o c 		/.$setResult($_endproc);./
		| r e t u r n    		/.$setResult($_return);./
		| c a l l			/.$setResult($_call);./
		| p a r d o			/.$setResult($_pardo);./
		| e n d p a r d o	 	/.$setResult($_endpardo);./
-- 	    | a l s o			/.$setResult($_also);./
		| d o				/.$setResult($_do);./
		| i n             /.$setResult($_in);./ 
		| e n d d o 			/.$setResult($_enddo);./
		| c y c l e			/.$setResult($_cycle);./
		| e x i t			/.$setResult($_exit);./
		| i f           		/.$setResult($_if);./
		| e l s e		        /.$setResult($_else);./
		| e n d i f	           	/.$setResult($_endif);./
		-- | t r u e 			/.$setResult($_true);./
		-- | f a l s e 			/.$setResult($_false);./
		| p u t          		/.$setResult($_put);./
		| g e t				/.$setResult($_get);./
		| p r e p a r e			/.$setResult($_prepare);./
		| r e q u e s t			/.$setResult($_request);./
		| p r e q u e s t		/.$setResult($_prequest);./
		| c o l l e c t i v e		/.$setResult($_collective);./
		| e x e c u t e			/.$setResult($_execute);./
		| a o i n d e x			/.$setResult($_aoindex);./
		| m o i n d e x			/.$setResult($_moindex);./
		| m o a i n d e x		/.$setResult($_moaindex);./
		| m o b i n d e x		/.$setResult($_mobindex);./
		| i n d e x			/.$setResult($_index);./
		| l a i n d e x			/.$setResult($_laindex);./
		| s u b i n d e x		/.$setResult($_subindex);./
		| o f 							 /.$setResult($_of);./
		| s c a l a r			/.$setResult($_scalar);./
		| s t a t i c			/.$setResult($_static);./
		| t e m p			/.$setResult($_temp);./
		| l o c a l			/.$setResult($_local);./
		| d i s t r i b u t e d		/.$setResult($_distributed);./
		| s e r v e d			/.$setResult($_served);./
		| c r e a t e 			/.$setResult($_create);./
		| a l l o c a t e		/.$setResult($_allocate);./
		| d e l e t e			/.$setResult($_delete);./
		| d e a l l o c a t e 	        /.$setResult($_deallocate);./
--		| c o m p u t  e  _ i n t e g r a l s        /.$setResult($_compute_integrals);./
		| d e s t r o y                 /.$setResult($_destroy);./
        | w h e r e                     /.$setResult($_where);./
		| i m p o r t                        /.$setResult($_import);./								  
		| i n t                        /.$setResult($_int);./												 
		| p r e d e f i n e d                        /.$setResult($_predefined);./		
        | s p e c i a l             /. $setResult($_special);./			
		| s e r v e r _ b a r r i e r           /. $setResult($_server_barrier);./
		| s i p _ b a r r i e r      /.$setResult($_sip_barrier);./		
--	    | c o n s t a n t        /.$setResult($_constant);./				   
--	    | c o n f i g u r e        /.$setResult($_configure);./	   
	    | s e c t i o n        /.$setResult($_section);./		
        | e n d s e c t i o n        /.$setResult($_endsection);./		
        | p r i n t         /.$setResult($_print);./	 
        | p r i n t  l n       /.$setResult($_println);./
		| p r i n t _ i n d e x  /.$setResult($_print_index);./
		| p r i n t _ s c a l a r 	 /.$setResult($_print_scalar);./		
		| p r i n t _ i n t /.$setResult($_print_int);./							 
--		| s i p _ c o n s i s t e n t	 /.$setResult($_sip_consistent);./
--        | s c o p e d _ e x t e n t	 /.$setResult($_scoped_extent);./
        | c o n t i g u o u s  /.$setResult($_contiguous);./
--        | a u t o _ a l l o c a t e   /.$setResult($_auto_allocate);./ 			
		| g p u _ o n  /.$setResult($_gpu_on);./
	    | g p u _ o f f   /.$setResult($_gpu_off);./					
	    | g p u _ a l l o c a t e   /.$setResult($_gpu_allocate);./				
	    | g p u _ f r e e  /.$setResult($_gpu_free);./
		| g p u _ g e t  /.$setResult($_gpu_get);./
		| g p u _ p u t  /.$setResult($_gpu_put);./			
	    | s e t _ p e r s i s t e n t        /.$setResult($_set_persistent);./									   
	    | r e s t o r e _ p e r s i s t e n t       /.$setResult($_restore_persistent);./																	   											  
        | s p a r s e      /.$setResult($_sparse);./
		| a s s e r t _ s a m e  /.$setResult($_assert_same);./				 

														
	%End
