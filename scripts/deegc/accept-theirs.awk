# A quick script to resolve conficts by always removing the HEAD block.
BEGIN { list = 1; }

/^<<<<<<< HEAD$/ {
    list = 0;
    next;
}

/^=======$/ {
    if ( ! list ) {
        list = 1;
        next;
    }
}

/^>>>>>>>/ { next; }

{
    if ( list )
        print $0;

    next;
}
