#!/usr/bin/perl

my $find = "getInternalAttributeValue";
my $replace = "getInternalValue()";

# Find the line with $find string.  Group $1 will be text before $find, $3 will be after.
if ( /(^.*)(\.$find)(\(.*)/xg ) {
    print $_;
    my $str = $1;
    my $end = $3;
    
    # Now find text inside the first matching parens.  This will grab everything between
    # them, including other parens.
    $end./(\((?:[^()]*+|(?0))*\))/x;

    # Now print new line with $find replaced with $replace.  Note that $` will print
    # out everything after the closing parent found above.
    print "$str", ".getAttribute", "$1", ".$replace$'\n";
}
