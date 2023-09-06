package fitch.dexter.timeintervalcomparison;

public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String meridian;

    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        if (!validateHours(hours) || !validateMinutes(minutes) || !validateMeridian(meridian)) {
            throw new InvalidTime("Invalid input. Verify that hours are 1-12, minutes are 0-59, and meridian is AM or PM.");
        }

        if (hours == 12 && meridian.equalsIgnoreCase("AM")) {
            this.hours = 0;
        } else if (hours >= 1 && hours <= 11 && meridian.equalsIgnoreCase("PM")) {
            this.hours = hours + 12;
        } else {
            this.hours = hours;
        }

        this.minutes = minutes;
        this.meridian = meridian.toUpperCase();
    }

    public Time(String time) throws InvalidTime { // Expected format of `time`: HH:MM AM
        String[] hoursMinutesMeridian = time.split(":");
        if (hoursMinutesMeridian.length != 2) {
            throw new InvalidTime("Invalid time format.");
        }

        String[] minutesMeridian = hoursMinutesMeridian[1].split(" ");
        if (minutesMeridian.length != 2) {
            throw new InvalidTime("Invalid time format.");
        }

        int hours;
        int minutes;
        String meridian = minutesMeridian[1];

        try {
            hours = Integer.parseInt(hoursMinutesMeridian[0]);
        } catch (NumberFormatException ex) {
            throw new InvalidTime("Invalid value for hours.");
        }

        try {
            minutes = Integer.parseInt(minutesMeridian[0]);
        } catch (NumberFormatException ex) {
            throw new InvalidTime("Invalid value for minutes.");
        }

        if (!validateHours(hours) || !validateMinutes(minutes) || !validateMeridian(meridian)) {
            throw new InvalidTime("Invalid input. Verify that hours are 1-12, minutes are 0-59, and meridian is AM or PM.");
        }

        if (hours == 12 && meridian.equalsIgnoreCase("AM")) {
            this.hours = 0;
        } else if (hours >= 1 && hours <= 11 && meridian.equalsIgnoreCase("PM")) {
            this.hours = hours + 12;
        } else {
            this.hours = hours;
        }

        this.minutes = minutes;
        this.meridian = meridian;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getMeridian() {
        return meridian;
    }

    @Override
    public int compareTo(Time anotherTime) {
        if (!this.getMeridian().equals(anotherTime.getMeridian())) {
            return this.getMeridian().compareTo(anotherTime.getMeridian());
        }
        if (this.getHours() != anotherTime.getHours()) {
            return Integer.compare(this.getHours(), anotherTime.getHours());
        }
        return Integer.compare(this.getMinutes(), anotherTime.getMinutes());

        // Explanation of returns:
        // Example: time1.compareTo(time2)
        // If time1's meridian = AM && time2's meridian = AM: 0
        // If time1's meridian = PM && time2's meridian = PM: 0
        // If time1's meridian = AM && time2's meridian = PM: -15
        // If time1's meridian = PM && time2's meridian = AM: 15
        // If time1's hours > time2's hours: 1
        // If time1's hours < time2's hours: -1
        // If time1's hours == time2's hours: 0
        // If time1's minutes > time2's minutes: 1
        // If time1's minutes < time2's minutes: -1
        // If time1's minutes == time2's minutes: 0

    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian.toUpperCase());
    }

    private boolean validateHours(int hours) {
        return hours >= 1 && hours <= 12;
    }

    private boolean validateMinutes(int minutes) {
        return minutes >= 0 && minutes <= 59;
    }

    private boolean validateMeridian(String meridian) {
        return meridian.equalsIgnoreCase("AM") || meridian.equalsIgnoreCase("PM");
    }
}

