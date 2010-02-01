#!/usr/bin/perl

#basic proof of concept, takes input line, provides indexed output

use Modern::Perl;
use Data::Dumper;
use List::MoreUtils qw/uniq/;

my @words;
my @results;
print "> ";
while(my $line = <>) {
   chomp $line;
   if($line !~ /^[a-zA-Z ]+$/) {
      say "bad input: $line";
      print "> ";
      next;
   }
   @words = split /\s+/, $line;
   for(0..@words - 1) {
      my $w = shift @words;
      push @words, $w;
      push @results, join ' ', @words;
   }
   @results = uniq(@results);
   @results = sort {uc($a) cmp uc($b)} @results;
   say Dumper(\@results);
   print "> ";
}
