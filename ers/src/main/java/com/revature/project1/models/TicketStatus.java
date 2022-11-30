package com.revature.project1.models;

public enum TicketStatus {

    // Definitions of zodiac signs
    PENDING,
    APPROVED,
    DENIED;

    public static TicketStatus fromInteger(int x) {
        switch (x) {
            case 1:
                return PENDING;
            case 2:
                return APPROVED;
            case 3:
                return DENIED;

        }
        return null;
    }

    public Integer toInteger() {

        switch (this) {
            case PENDING:
                return 1;
            case APPROVED:
                return 2;
            case DENIED:
                return 3;

        }
        return null;
    }

    public static Integer toInteger(String s) {

        switch (TicketStatus.valueOf(s.toUpperCase())) {
            case PENDING:
                return 1;
            case APPROVED:
                return 2;
            case DENIED:
                return 3;

        }
        return null;
    }

    public String getStatusName() {
        return this.toString();
    }
}
