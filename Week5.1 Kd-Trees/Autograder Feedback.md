_See the Assessment Guide for information on how to interpret this report._  

## ASSESSMENT SUMMARY

<pre>
Compilation:  <font color="#009900">PASSED</font>
API:          <font color="#009900">PASSED</font>

SpotBugs:     <font color="#009900">PASSED</font>
PMD:          <font color="#009900">PASSED</font>
Checkstyle:   <font color="#009900">PASSED</font>

Correctness:  <font color="#199900">30/35 tests passed</font>
Memory:       <font color="#009900">16/16 tests passed</font>
Timing:       <font color="#249900">34/42 tests passed</font>

Aggregate score: 87.62%
[ Compilation: 5%, API: 5%, Style: 0%, Correctness: 60%, Timing: 10%, Memory: 20% ]
</pre>

## ASSESSMENT DETAILS

<pre>
The following files were submitted:
----------------------------------
6.8K Nov 20 12:32 KdTree.java
4.1K Nov 20 12:32 PointSET.java

********************************************************************************
*  COMPILING                                                                    
********************************************************************************

% javac PointSET.java
*-----------------------------------------------------------

% javac KdTree.java
*-----------------------------------------------------------

================================================================

Checking the APIs of your programs.
*-----------------------------------------------------------
PointSET:

KdTree:

================================================================

********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS                                       
********************************************************************************

% spotbugs *.class
*-----------------------------------------------------------

================================================================

% pmd .
*-----------------------------------------------------------

================================================================

% checkstyle *.java
*-----------------------------------------------------------

% custom checkstyle checks for PointSET.java
*-----------------------------------------------------------

% custom checkstyle checks for KdTree.java
*-----------------------------------------------------------

================================================================

********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of PointSET
*-----------------------------------------------------------
Running 8 total tests.

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m

Test 1: insert n random points; check size() and isEmpty() after each insertion
        (size may be less than n because of duplicates)
  * 5 random points in a 1-by-1 grid
  * 50 random points in a 8-by-8 grid
  * 100 random points in a 16-by-16 grid
  * 1000 random points in a 128-by-128 grid
  * 5000 random points in a 1024-by-1024 grid
  * 50000 random points in a 65536-by-65536 grid
==> passed

Test 2: insert n random points; check contains() with random query points
  * 1 random points in a 1-by-1 grid
  * 10 random points in a 4-by-4 grid
  * 20 random points in a 8-by-8 grid
  * 10000 random points in a 128-by-128 grid
  * 100000 random points in a 1024-by-1024 grid
  * 100000 random points in a 65536-by-65536 grid
==> passed

Test 3: insert random points; check nearest() with random query points
  * 10 random points in a 4-by-4 grid
  * 15 random points in a 8-by-8 grid
  * 20 random points in a 16-by-16 grid
  * 100 random points in a 32-by-32 grid
  * 10000 random points in a 65536-by-65536 grid
==> passed

Test 4: insert random points; check range() with random query rectangles
  * 2 random points and random rectangles in a 2-by-2 grid
  * 10 random points and random rectangles in a 4-by-4 grid
  * 20 random points and random rectangles in a 8-by-8 grid
  * 100 random points and random rectangles in a 16-by-16 grid
  * 1000 random points and random rectangles in a 64-by-64 grid
  * 10000 random points and random rectangles in a 128-by-128 grid
==> passed

Test 5: call methods before inserting any points
 * size() and isEmpty()
 * contains()
 * nearest()
 * range()
==> passed

Test 6: call methods with null argument
  * insert()
  * contains()
  * range()
  * nearest()
==> passed

Test 7: check intermixed sequence of calls to insert(), isEmpty(),
        size(), contains(), range(), and nearest() with
        probabilities (p1, p2, p3, p4, p5, p6, p7), respectively
  * 10000 calls with random points in a 1-by-1 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  * 10000 calls with random points in a 16-by-16 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  * 10000 calls with random points in a 128-by-128 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  * 10000 calls with random points in a 1024-by-1024 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  * 10000 calls with random points in a 8192-by-8192 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
  * 10000 calls with random points in a 65536-by-65536 grid
    and probabilities (0.3, 0.1, 0.1, 0.1, 0.2, 0.2)
==> passed

Test 8: check that two PointSET objects can be created at the same time
==> passed

Total: 8/8 tests passed!

================================================================
Testing correctness of KdTree
*-----------------------------------------------------------
Running 27 total tests.

In the tests below, we consider three classes of points and rectangles.

  * Non-degenerate points: no two points (or rectangles) share either an
                           x-coordinate or a y-coordinate

  * Distinct points:       no two points (or rectangles) share both an
                           x-coordinate and a y-coordinate

  * General points:        no restrictions on the x-coordinates or y-coordinates
                           of the points (or rectangles)

A point in an m-by-m grid means that it is of the form (i/m, j/m),
where i and j are integers between 0 and m (inclusive).

Test 1a: insert points from file; check size() and isEmpty() after each insertion
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
  * input25.txt
  * input50.txt
==> passed

Test 1b: insert non-degenerate points; check size() and isEmpty() after each insertion
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 50 random non-degenerate points in a 128-by-128 grid
  * 500 random non-degenerate points in a 1024-by-1024 grid
  * 50000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 1c: insert distinct points; check size() and isEmpty() after each insertion
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 8-by-8 grid
  * 20 random distinct points in a 16-by-16 grid
  * 10000 random distinct points in a 128-by-128 grid
  * 100000 random distinct points in a 1024-by-1024 grid
  * 100000 random distinct points in a 65536-by-65536 grid
==> passed

Test 1d: insert general points; check size() and isEmpty() after each insertion
  * 5 random general points in a 1-by-1 grid
  * 10 random general points in a 4-by-4 grid
  * 50 random general points in a 8-by-8 grid
  * 100000 random general points in a 16-by-16 grid
  * 100000 random general points in a 128-by-128 grid
  * 100000 random general points in a 1024-by-1024 grid
==> passed

Test 2a: insert points from file; check contains() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 2b: insert non-degenerate points; check contains() with random query points
  * 1 random non-degenerate points in a 1-by-1 grid
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 20 random non-degenerate points in a 32-by-32 grid
  * 500 random non-degenerate points in a 1024-by-1024 grid
  * 10000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 2c: insert distinct points; check contains() with random query points
  * 1 random distinct points in a 1-by-1 grid
  * 10 random distinct points in a 4-by-4 grid
  * 20 random distinct points in a 8-by-8 grid
  * 10000 random distinct points in a 128-by-128 grid
  * 100000 random distinct points in a 1024-by-1024 grid
  * 100000 random distinct points in a 65536-by-65536 grid
==> passed

Test 2d: insert general points; check contains() with random query points
  * 10000 random general points in a 1-by-1 grid
  * 10000 random general points in a 16-by-16 grid
  * 10000 random general points in a 128-by-128 grid
  * 10000 random general points in a 1024-by-1024 grid
==> passed

Test 3a: insert points from file; check range() with random query rectangles
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 3b: insert non-degenerate points; check range() with random query rectangles
  * 1 random non-degenerate points and random rectangles in a 2-by-2 grid
  * 5 random non-degenerate points and random rectangles in a 8-by-8 grid
  * 10 random non-degenerate points and random rectangles in a 16-by-16 grid
  * 20 random non-degenerate points and random rectangles in a 32-by-32 grid
  * 500 random non-degenerate points and random rectangles in a 1024-by-1024 grid
  * 10000 random non-degenerate points and random rectangles in a 65536-by-65536 grid
==> passed

Test 3c: insert distinct points; check range() with random query rectangles
  * 2 random distinct points and random rectangles in a 2-by-2 grid
  * 10 random distinct points and random rectangles in a 4-by-4 grid
  * 20 random distinct points and random rectangles in a 8-by-8 grid
  * 100 random distinct points and random rectangles in a 16-by-16 grid
  * 1000 random distinct points and random rectangles in a 64-by-64 grid
  * 10000 random distinct points and random rectangles in a 128-by-128 grid
==> passed

Test 3d: insert general points; check range() with random query rectangles
  * 5000 random general points and random rectangles in a 2-by-2 grid
  * 5000 random general points and random rectangles in a 16-by-16 grid
  * 5000 random general points and random rectangles in a 128-by-128 grid
  * 5000 random general points and random rectangles in a 1024-by-1024 grid
==> passed

Test 3e: insert random points; check range() with tiny rectangles
         enclosing each point
  * 5 tiny rectangles and 5 general points in a 2-by-2 grid
  * 10 tiny rectangles and 10 general points in a 4-by-4 grid
  * 20 tiny rectangles and 20 general points in a 8-by-8 grid
  * 5000 tiny rectangles and 5000 general points in a 128-by-128 grid
  * 5000 tiny rectangles and 5000 general points in a 1024-by-1024 grid
  * 5000 tiny rectangles and 5000 general points in a 65536-by-65536 grid
==> passed

Test 4a: insert points from file; check range() with random query rectangles
         and check traversal of k-d tree
  * input5.txt
    - student range() returns correct set of points
    - student   range():  B C 
    - reference range():  B C 
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.19, 0.65] x [0.18, 0.51]
    - sequence of points inserted: 
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6
    - student k-d tree nodes involved in calls to Point2D methods:
      A B C D E 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A B C D 
    - failed on trial 1 of 1000

  * input10.txt
    - student range() returns correct set of points
    - student   range():  B 
    - reference range():  B 
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.45, 0.75] x [0.32, 0.88]
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - student k-d tree nodes involved in calls to Point2D methods:
      A B D G J C F H I 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A B G J H I 
    - failed on trial 1 of 1000

==> <font color="#990000">**FAILED**</font>

Test 4b: insert non-degenerate points; check range() with random query rectangles
         and check traversal of k-d tree
  * 3 random non-degenerate points and 1000 random rectangles in a 4-by-4 grid
    - student range() returns correct set of points
    - student   range():  empty
    - reference range():  empty
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.0, 0.5] x [0.0, 0.25]
    - sequence of points inserted: 
      A  0.25 1.0
      B  0.75 0.75
      C  1.0 0.5
    - student k-d tree nodes involved in calls to Point2D methods:
      A B 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A B C 
    - failed on trial 1 of 1000

  * 6 random non-degenerate points and 1000 random rectangles in a 8-by-8 grid
    - student range() returns correct set of points
    - student   range():  empty
    - reference range():  empty
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.625, 1.0] x [0.25, 0.375]
    - sequence of points inserted: 
      A  0.75 0.875
      B  0.25 0.0
      C  0.875 0.5
      D  0.125 0.125
      E  0.0 0.625
      F  0.375 0.75
    - student k-d tree nodes involved in calls to Point2D methods:
      A B C 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A B D F C 
    - failed on trial 1 of 1000

  * 10 random non-degenerate points and 1000 random rectangles in a 16-by-16 grid
    - student range() returns correct set of points
    - student   range():  empty
    - reference range():  empty
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.625, 0.75] x [0.5, 0.75]
    - sequence of points inserted: 
      A  0.0625 0.25
      B  0.125 0.5625
      C  0.0 0.3125
      D  0.375 0.0
      E  0.9375 0.6875
      F  0.6875 0.0625
      G  0.5625 0.1875
      H  0.3125 0.875
      I  0.25 0.625
      J  0.875 0.125
    - student k-d tree nodes involved in calls to Point2D methods:
      A B E I H 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A B D F G J E H I 
    - failed on trial 1 of 1000

  * 20 random non-degenerate points and 1000 random rectangles in a 32-by-32 grid
    - student range() returns correct set of points
    - student   range():  C E K D 
    - reference range():  C E K D 
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.125, 0.96875] x [0.21875, 0.53125]
    - sequence of points inserted: 
      A  0.1875 0.5625
      B  0.34375 0.625
      C  0.8125 0.375
      D  0.90625 0.40625
      E  0.6875 0.4375
      F  0.0 0.59375
      G  0.09375 0.75
      H  0.625 0.0
      I  0.25 0.71875
      J  0.03125 0.34375
      K  0.78125 0.25
      L  0.40625 0.1875
      M  0.65625 0.90625
      N  0.0625 0.6875
      O  0.75 0.96875
      P  0.15625 0.15625
      Q  0.53125 0.8125
      R  0.9375 0.84375
      S  0.84375 0.9375
      T  0.3125 0.78125
    - student k-d tree nodes involved in calls to Point2D methods:
      A C E H J L P K D 
    - reference k-d tree nodes involved in calls to Point2D methods:
      A F J P B C E H L K D 
    - failed on trial 1 of 1000

  * 30 random non-degenerate points and 1000 random rectangles in a 64-by-64 grid
    - student range() returns correct set of points
    - student   range():  A T 
    - reference range():  A T 
    - performs incorrect traversal of k-d tree during call to range()
    - query rectangle = [0.46875, 0.671875] x [0.515625, 0.65625]
    - number of entries in student   solution: 8
    - number of entries in reference solution: 9
    - 3 extra entries in student solution, including:
      '(0.1875, 0.890625)'

    - 4 missing entries in student solution, including:
      '(0.734375, 0.78125)'

    - failed on trial 1 of 1000

==> <font color="#990000">**FAILED**</font>

Test 5a: insert points from file; check nearest() with random query points
  * input0.txt
  * input1.txt
  * input5.txt
  * input10.txt
==> passed

Test 5b: insert non-degenerate points; check nearest() with random query points
  * 5 random non-degenerate points in a 8-by-8 grid
  * 10 random non-degenerate points in a 16-by-16 grid
  * 20 random non-degenerate points in a 32-by-32 grid
  * 30 random non-degenerate points in a 64-by-64 grid
  * 10000 random non-degenerate points in a 65536-by-65536 grid
==> passed

Test 5c: insert distinct points; check nearest() with random query points
  * 10 random distinct points in a 4-by-4 grid
  * 15 random distinct points in a 8-by-8 grid
  * 20 random distinct points in a 16-by-16 grid
  * 100 random distinct points in a 32-by-32 grid
  * 10000 random distinct points in a 65536-by-65536 grid
==> passed

Test 5d: insert general points; check nearest() with random query points
  * 10000 random general points in a 16-by-16 grid
  * 10000 random general points in a 128-by-128 grid
  * 10000 random general points in a 1024-by-1024 grid

        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        OperationCountLimitExceededException
        Number of calls to methods in Point2D exceeds limit: 1000000000
        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

==> <font color="#990000">**FAILED**</font>

Test 6a: insert points from file; check nearest() with random query points
         and check traversal of k-d tree
  * input5.txt
    - student   nearest() = (0.9, 0.6)
    - reference nearest() = (0.9, 0.6)
    - performs incorrect traversal of k-d tree during call to nearest()
    - query point = (0.78, 0.85)
    - sequence of points inserted: 
      A  0.7 0.2
      B  0.5 0.4
      C  0.2 0.3
      D  0.4 0.7
      E  0.9 0.6
    - student sequence of k-d tree nodes involved in calls to Point2D methods:
      A B C D E 
    - reference sequence of k-d tree nodes involved in calls to Point2D methods:
      A E B D 
    - failed on trial 1 of 1000

  * input10.txt
    - student   nearest() = (0.32, 0.708)
    - reference nearest() = (0.32, 0.708)
    - performs incorrect traversal of k-d tree during call to nearest()
    - query point = (0.38, 0.95)
    - sequence of points inserted: 
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
    - student sequence of k-d tree nodes involved in calls to Point2D methods:
      A B D G J C E F H I 
    - reference sequence of k-d tree nodes involved in calls to Point2D methods:
      A B H I C F 
    - failed on trial 1 of 1000

==> <font color="#990000">**FAILED**</font>

Test 6b: insert non-degenerate points; check nearest() with random query points
         and check traversal of k-d tree
  * 5 random non-degenerate points in a 8-by-8 grid
    - student   nearest() = (0.625, 0.625)
    - reference nearest() = (0.625, 0.625)
    - performs incorrect traversal of k-d tree during call to nearest()
    - query point = (0.875, 0.25)
    - sequence of points inserted: 
      A  0.25 0.75
      B  0.375 0.125
      C  0.0 0.875
      D  0.625 0.625
      E  1.0 1.0
    - student sequence of k-d tree nodes involved in calls to Point2D methods:
      A B D C E 
    - reference sequence of k-d tree nodes involved in calls to Point2D methods:
      A B D E 
    - failed on trial 1 of 1000

  * 10 random non-degenerate points in a 16-by-16 grid
    - student   nearest() = (0.9375, 0.25)
    - reference nearest() = (0.9375, 0.25)
    - performs incorrect traversal of k-d tree during call to nearest()
    - query point = (0.8125, 0.0)
    - sequence of points inserted: 
      A  0.9375 0.25
      B  0.6875 0.875
      C  0.5 0.6875
      D  0.625 0.5625
      E  0.75 0.375
      F  0.3125 0.625
      G  0.375 0.5
      H  0.5625 0.75
      I  0.0 0.4375
      J  0.1875 0.3125
    - student sequence of k-d tree nodes involved in calls to Point2D methods:
      A B C D F G I J H E 
    - reference sequence of k-d tree nodes involved in calls to Point2D methods:
      A B C D E 
    - failed on trial 1 of 1000

  * 20 random non-degenerate points in a 32-by-32 grid
    - student   nearest() = (0.0625, 0.125)
    - reference nearest() = (0.0625, 0.125)
    - performs incorrect traversal of k-d tree during call to nearest()
    - query point = (0.0, 0.25)
    - sequence of points inserted: 
      A  0.875 0.71875
      B  0.28125 0.84375
      C  0.0625 0.125
      D  0.96875 0.6875
      E  0.6875 0.8125
      F  0.90625 0.96875
      G  0.25 0.75
      H  0.3125 0.53125
      I  0.4375 0.625
      J  0.34375 0.3125
      K  0.1875 0.09375
      L  0.625 0.46875
      M  0.78125 0.21875
      N  0.8125 0.40625
      O  0.46875 0.15625
      P  0.71875 0.1875
      Q  0.75 0.375
      R  0.84375 0.28125
      S  0.375 0.65625
      T  0.9375 0.4375
    - student sequence of k-d tree nodes involved in calls to Point2D methods:
      A C D H K I J L M O P Q N R T S B G E F 
    - reference sequence of k-d tree nodes involved in calls to Point2D methods:
      A B C E G K 
    - failed on trial 1 of 1000

  * 30 random non-degenerate points in a 64-by-64 grid
    - student   nearest() = (0.296875, 0.671875)
    - reference nearest() = (0.296875, 0.671875)
    - performs incorrect traversal of k-d tree during call to nearest()
    - number of student   entries = 30
    - number of reference entries = 7
    - entry 1 of the two sequences are not equal
    - student   entry 1 = (1.0, 0.015625)
    - reference entry 1 = (0.453125, 0.171875)

    - failed on trial 1 of 1000

  * 50 random non-degenerate points in a 128-by-128 grid
    - student   nearest() = (0.7578125, 0.9765625)
    - reference nearest() = (0.7578125, 0.9765625)
    - performs incorrect traversal of k-d tree during call to nearest()
    - number of student   entries = 50
    - number of reference entries = 9
    - entry 1 of the two sequences are not equal
    - student   entry 1 = (0.0859375, 0.3984375)
    - reference entry 1 = (0.9921875, 0.96875)

    - failed on trial 1 of 1000

  * 1000 random non-degenerate points in a 2048-by-2048 grid
    - student   nearest() = (0.61279296875, 0.79931640625)
    - reference nearest() = (0.61279296875, 0.79931640625)
    - performs incorrect traversal of k-d tree during call to nearest()
    - number of student   entries = 1000
    - number of reference entries = 12
    - entry 2 of the two sequences are not equal
    - student   entry 2 = (0.0283203125, 0.2421875)
    - reference entry 2 = (0.546875, 0.87255859375)

    - failed on trial 1 of 1000

==> <font color="#990000">**FAILED**</font>

Test 7: check with no points
  * size() and isEmpty()
  * contains()
  * nearest()
  * range()
==> passed

Test 8: check that the specified exception is thrown with null arguments
  * argument to insert() is null
  * argument to contains() is null
  * argument to range() is null
  * argument to nearest() is null
==> passed

Test 9a: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with non-degenerate points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with non-degenerate points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9b: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with distinct points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with distinct points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 9c: check intermixed sequence of calls to insert(), isEmpty(),
         size(), contains(), range(), and nearest() with probabilities
         (p1, p2, p3, p4, p5, p6), respectively
  * 20000 calls with general points in a 1-by-1 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 16-by-16 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 128-by-128 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 1024-by-1024 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 8192-by-8192 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
  * 20000 calls with general points in a 65536-by-65536 grid
    and probabilities (0.3, 0.05, 0.05, 0.2, 0.2, 0.2)
==> passed

Test 10: insert n random points into two different KdTree objects;
        check that repeated calls to size(), contains(), range(),
        and nearest() with the same arguments yield same results
  * 10 random general points in a 4-by-4 grid
  * 20 random general points in a 8-by-8 grid
  * 100 random general points in a 128-by-128 grid
  * 1000 random general points in a 65536-by-65536 grid
==> passed

Total: 22/27 tests passed!

================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Point2D
*-----------------------------------------------------------
Memory of Point2D object = 32 bytes

================================================================

Analyzing memory of RectHV
*-----------------------------------------------------------
Memory of RectHV object = 48 bytes

================================================================

Analyzing memory of PointSET
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a PointSET with n points (including Point2D and RectHV objects).
Maximum allowed memory is 96n + 200 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          240                264
=> passed        2          336                360
=> passed        5          624                648
=> passed       10         1104               1128
=> passed       25         2544               2568
=> passed      100         9744               9768
=> passed      400        38544              38568
=> passed      800        76944              76968
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 96.00 n + 144.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 96.00 n + 168.00  (R^2 = 1.000)

================================================================

Analyzing memory of KdTree
*-----------------------------------------------------------
Running 8 total tests.

Memory usage of a KdTree with n points (including Point2D and RectHV objects).
Maximum allowed memory is 312n + 192 bytes.

                 n       student (bytes)    reference (bytes)
--------------------------------------------------------------
=> passed        1          112                160
=> passed        2          192                288
=> passed        5          432                672
=> passed       10          832               1312
=> passed       25         2032               3232
=> passed      100         8032              12832
=> passed      400        32032              51232
=> passed      800        64032             102432
==> 8/8 tests passed

Total: 8/8 tests passed!

Estimated student   memory (bytes) = 80.00 n + 32.00  (R^2 = 1.000)
Estimated reference memory (bytes) = 128.00 n + 32.00  (R^2 = 1.000)

================================================================

********************************************************************************
*  TIMING
********************************************************************************

Timing PointSET
*-----------------------------------------------------------
Running 14 total tests.

Inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000    1222490         
=> passed   320000    1107257         
=> passed   640000    1024351         
=> passed  1280000     843603         
==> 4/4 tests passed

Performing contains() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed   160000     641333         
=> passed   320000     569398         
=> passed   640000     569552         
=> passed  1280000     457936         
==> 4/4 tests passed

Performing range() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       4835         
=> passed    20000       1666         
=> passed    40000        748         
==> 3/3 tests passed

Performing nearest() queries after inserting n points into a PointSET

               n      ops per second
----------------------------------------
=> passed    10000       7124         
=> passed    20000       2013         
=> passed    40000        855         
==> 3/3 tests passed

Total: 14/14 tests passed!

================================================================

Timing KdTree
*-----------------------------------------------------------
Running 28 total tests.

Test 1a-d: Insert n points into a 2d tree. The table gives the average number of calls
           to methods in RectHV and Point per call to insert().

                                                                                                Point2D
               n      ops per second       RectHV()           x()               y()             equals()
----------------------------------------------------------------------------------------------------------------
=> passed   160000    1242021               0.0              42.7              44.7              21.9         
=> passed   320000    1065521               0.0              42.4              44.4              21.7         
=> passed   640000     896466               0.0              44.6              46.6              22.8         
=> passed  1280000     722104               0.0              48.9              50.9              24.9         
==> 4/4 tests passed

Test 2a-h: Perform contains() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to contains().

                                                                               Point2D
               n      ops per second       x()               y()               equals()
-----------------------------------------------------------------------------------------------
=> passed    10000     948420              17.9              18.9              18.4         
=> passed    20000     963739              17.8              18.8              18.3         
=> passed    40000     838460              21.3              22.3              21.8         
=> passed    80000     770338              21.6              22.6              22.1         
=> passed   160000     647581              22.1              23.1              22.6         
=> passed   320000     528178              24.2              25.2              24.7         
=> passed   640000     391732              24.8              25.8              25.3         
=> passed  1280000     401976              26.9              27.9              27.4         
==> 8/8 tests passed

Test 3a-h: Perform range() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to range().

               n      ops per second       intersects()      contains()        x()               y()
---------------------------------------------------------------------------------------------------------------
=> passed    10000     473081               0.0              30.0              77.5              40.4         
=> passed    20000     445566               0.0              33.0              86.0              44.4         
=> passed    40000     396864               0.0              36.2              94.3              52.0         
=> passed    80000     330520               0.0              36.6              95.4              50.2         
=> passed   160000     258868               0.0              42.2             110.6              61.7         
=> passed   320000     210357               0.0              41.4             107.1              53.1         
=> passed   640000     199529               0.0              41.2             106.3              53.4         
=> passed  1280000     186743               0.0              44.4             115.2              57.0         
==> 8/8 tests passed

Test 4a-h: Perform nearest() queries after inserting n points into a 2d tree. The table gives
           the average number of calls to methods in RectHV and Point per call to nearest().

                                         Point2D                 RectHV
               n      ops per second     distanceSquaredTo()     distanceSquaredTo()        x()               y()
------------------------------------------------------------------------------------------------------------------------

        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        OperationCountLimitExceededException
        Number of calls to methods in Point2D exceeds limit: 1000000000
        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

==> 0/8 tests passed

Total: 20/28 tests passed!

================================================================

</pre>
