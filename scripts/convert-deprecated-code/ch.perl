#!/usr/bin/perl

my $find = $ENV{'ch_find'};
my $replace = $ENV{'ch_replace'};
my $printall = $ENV{'ch_print'};
my $args = $ENV{'ch_args'};

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
    # them, including other parens.  $1 will be everything inside the parens.
    $end =~ /(\((?:[^()]*+|(?0))*\))/x;

    #print "str=$str\n";
    #print "1=$1\n";
    #print "replace=$replace\n";
    #print "end=$'\n";

    # Now print new line with $find replaced with $replace.  Note that $` will print
    # out everything after the closing parent found above.
    if ( $args eq 0 ) {
	print "$str", ".getAttribute", "$1", ".$replace$'\n";
    } elsif ( $args eq 1 ) {
	my $arg1, $argn;
	($arg1, $argn) = split(/,/, "$1", 2);
	print "$str", ".getAttribute", "$arg1", ").$replace($argn $'\n";
    } else {
        # v.cursor("").setAttributeFromAttribute("wCourseNumber", lClsLstC, "Course", "Number" );
	my $arg1, $arg2, $arg3, $argn;
	($arg1, $arg2, $arg3, $argn) = split(/,/, "$1", 4);
	print "$str", ".getAttribute", "$arg1", ").$replace($arg2.cursor( $arg3 ).getAttribute( $argn.getValue() )  $'\n";
   }
}
else
{
    if ( $printall eq "Y" ) {
	print "$_";
    }
}
