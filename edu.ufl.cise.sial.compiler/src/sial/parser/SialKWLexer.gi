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
persistent
special
server_barrier
sip_barrier
constant 
configure --reserved keyword
section
endsection
print
println
print_index
print_scalar

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
	    | p e r s i s t e n t                        /.$setResult($_persistent);./			
        | s p e c i a l             /. $setResult($_special);./			
		| s e r v e r _ b a r r i e r           /. $setResult($_server_barrier);./
		| s i p _ b a r r i e r      /.$setResult($_sip_barrier);./		
	    | c o n s t a n t        /.$setResult($_constant);./				   
	    | c o n f i g u r e        /.$setResult($_configure);./	   
	    | s e c t i o n        /.$setResult($_section);./		
        | e n d s e c t i o n        /.$setResult($_endsection);./		
        | p r i n t         /.$setResult($_print);./	 
        | p r i n t  l n       /.$setResult($_println);./
		| p r i n t _ i n d e x  /.$setResult($_print_index);./
		| p r i n t _ s c a l a r 	 /.$setResult($_print_scalar);./				  				 			   											  
%End
