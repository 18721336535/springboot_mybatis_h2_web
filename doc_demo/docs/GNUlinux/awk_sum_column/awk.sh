#bash awk.sh 1@2@3
str=$1
ary=(${str//@/ })
awk -v arry=$1 '
BEGIN { FS="|"; OFS=":";i=0;
#gsub("@"," ",arry);
split(arry,arrys,"@");
print "arry0--"arry;
print "arry1--"arrys[1];
print "arry2--"arrys[2];
}
{
i=i+1;
a=1+9 ;

#print "arry0--"arry;
#print "clm1--"$arrys[1];
for(i in arrys){
if( $arrys[i] ~ /^[0-9|\-]/ ) print arrys[i]" i am a number "$arrys[i];
else print arrys[i]" i am notttt a number "$arrys[i];
}

print "arry1--"arry >"./xx.log";


print NR"-is-"$N0>"./xx.log";
if (NR==2 || i==4) print "-I am 3-">"./xx.log";
name="--hell0-"FILENAME ;
#print a;print FILENAME,ARGC,FNR,FS,NF,NR,OFS,ORS,RS,a,$1,$3>"./xx.log";
print "--"NR>"./xx.log";

}
END {print "end--"NR >"./xx.log";}
' table_list_export.txt