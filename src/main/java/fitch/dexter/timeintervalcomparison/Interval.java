package fitch.dexter.timeintervalcomparison;

public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public boolean within(T value) {
        return value.compareTo(this.getStart()) >= 0 && value.compareTo(this.getEnd()) <= 0;
    }

    public boolean subinterval(Interval<T> anotherInterval) {
        return this.getStart().compareTo(anotherInterval.getStart()) <= 0 && this.getEnd().compareTo(anotherInterval.getEnd()) >= 0;
    }

    public boolean overlaps(Interval<T> anotherInterval) {
        return this.getEnd().compareTo(anotherInterval.getStart()) >= 0 && this.getStart().compareTo(anotherInterval.getEnd()) <= 0;
    }

// Test Logic
//    public static void main(String[] args) throws InvalidTime {
//        Time interval1Start = new Time("05:45 AM");
//        Time interval1End = new Time("09:45 AM");
//
//        Time interval2Start = new Time("06:45 AM");
//        Time interval2End = new Time("08:45 AM");
//
//        Time interval3Start = new Time("06:45 PM");
//        Time interval3End = new Time("08:45 PM");
//
//        Time interval4Start = new Time("01:00 AM");
//        Time interval4End = new Time("03:00 AM");
//
//        Time interval5Start = new Time("12:00 AM");
//        Time interval5End = new Time("02:00 AM");
//
//        Interval<Time> interval1 = new Interval<>(interval1Start, interval1End);
//        Interval<Time> interval2 = new Interval<>(interval2Start, interval2End);
//        Interval<Time> interval3 = new Interval<>(interval3Start, interval3End);
//        Interval<Time> interval4 = new Interval<>(interval4Start, interval4End);
//        Interval<Time> interval5 = new Interval<>(interval5Start, interval5End);
//
//        System.out.println(interval1.overlaps(interval2)); // Does interval2 overlap interval1? = true
//        System.out.println(interval2.overlaps(interval1)); // Does interval1 overlap interval2? = true
//        System.out.println(interval1.subinterval(interval2)); // Is interval2 a subinterval of interval1? = true
//        System.out.println(interval1.within(interval2Start)); // Is interval2Start within interval1? = true
//        System.out.println(interval1.overlaps(interval3)); // Does interval3 overlap interval1? = false
//        System.out.println(interval1.subinterval(interval3)); // Is interval3 a subinterval of interval1? = false
//        System.out.println(interval1.within(interval3Start)); // Is interval3Start within interval1? = false
//        System.out.println(interval4.within(interval4Start)); // Is interval4Start within interval5? = true
//        System.out.println(interval5.within(interval4Start)); // Is interval4Start within interval5? = true
//    }
}
