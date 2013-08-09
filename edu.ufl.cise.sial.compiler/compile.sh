SP=~/aces4_compiler/aces_sial/
for f in $(find $SP -iname "*.sialx")
do
if [ -a $f ]; then
#echo bash: compiling $f
DIR=$(dirname $f)
java -classpath lib/*:bin sial.compiler.SialCompiler  -sp $SP:$DIR $f 1>>std.log 2>>err.log
fi
done

