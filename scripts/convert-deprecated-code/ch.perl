#!/usr/bin/perl

my $find = $ENV{'ch_find'};
my $replace = $ENV{'ch_replace'};
my $printall = $ENV{'ch_print'};

# Find the line with $find string.  Group $1 will be text before $find, $3 will be after.
if ( /(^.*)(\.$find)(\(.*)/xg ) {
    if ( $printall ne "Y" ) {
	print "--\n";
	print $_;
    }

    my $str = $1;
    my $end = $3;
    
    #print "str=$str\n";
    #print "2=$2\n";
    #print "end=$end\n";

    # Now find text inside the first matching parens.  This will grab everything between
    # them, including other parens.
    $end =~ /(\((?:[^()]*+|(?0))*\))/x;

    #print "str=$str\n";
    #print "1=$1\n";
    #print "replace=$replace\n";
    #print "end=$'\n";

    # Now print new line with $find replaced with $replace.  Note that $` will print
    # out everything after the closing parent found above.
    print "$str", ".getAttribute", "$1", ".$replace$'\n";
}
else
{
    if ( $printall eq "Y" ) {
	print "$_";
    }
}
