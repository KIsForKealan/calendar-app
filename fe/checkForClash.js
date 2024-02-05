class TimePeriodRelation {

    static AFTER = new TimePeriodRelation("AFTER");
    static START_TOUCHING = new TimePeriodRelation("START_TOUCHING");
    static START_INSIDE = new TimePeriodRelation("START_INSIDE");
    static INSIDE_START_TOUCHING = new TimePeriodRelation("INSIDE_START_TOUCHING");
    static ENCLOSING_START_TOUCHING = new TimePeriodRelation("ENCLOSING_START_TOUCHING");
    static ENCLOSING = new TimePeriodRelation("ENCLOSING");
    static ENCLOSING_END_TOUCHING = new TimePeriodRelation("ENCLOSING_END_TOUCHING");
    static EXACT_MATCH = new TimePeriodRelation("EXACT_MATCH");
    static INSIDE = new TimePeriodRelation("INSIDE");
    static INSIDE_END_TOUCHING = new TimePeriodRelation("INSIDE_END_TOUCHING");
    static END_INSIDE = new TimePeriodRelation("END_INSIDE");
    static END_TOUCHING = new TimePeriodRelation("END_TOUCHING");
    static BEFORE = new TimePeriodRelation("BEFORE");
    static UNKNOWN = new TimePeriodRelation("UNKNOWN");

    constructor(type) {
        this.type = type;
    }

    toString() {
        return `${this.type}`;
    }

}

class TimePeriodEvent {

    constructor(start, end) {
        this.start = start;
        this.end = end;
    }

}

function checkForClash(first, second) {

    const firstStart = first.start.getTime();
    const firstEnd = first.end.getTime();
    const secondStart = second.start.getTime();
    const secondEnd = second.end.getTime();

    if ( (firstStart > secondEnd) && 
        (firstEnd > secondEnd) ) {
        return TimePeriodRelation.AFTER;
    }

    if ( (firstStart === secondEnd) && 
        (firstEnd > secondEnd) ) {
        return TimePeriodRelation.START_TOUCHING;
    }
    
    if ( (firstStart > secondStart) && 
        (firstStart < secondEnd) && 
        (firstEnd > secondEnd) ) {
        return TimePeriodRelation.START_INSIDE;
    }

    if ( (firstStart === secondStart) && 
        (firstEnd > secondStart) && 
        (firstEnd < secondEnd) ) {
        return TimePeriodRelation.INSIDE_START_TOUCHING;
    }

    if ( (firstStart === secondStart) && 
        (firstEnd > secondEnd) ) {
        return TimePeriodRelation.ENCLOSING_START_TOUCHING;
    }

    if ( (firstStart < secondStart) && 
        (firstEnd > secondEnd) ) {
        return TimePeriodRelation.ENCLOSING;
    }

    if ( (firstStart < secondStart) && 
        (firstEnd === secondEnd) ) {
        return TimePeriodRelation.ENCLOSING_END_TOUCHING;
    }

    if ( (firstStart === secondStart) && 
        (firstEnd === secondEnd) ) {
        return TimePeriodRelation.EXACT_MATCH;
    }

    if ( (firstStart > secondStart) && 
        (firstEnd < secondEnd) ) {
        return TimePeriodRelation.INSIDE;
    }

    if ( (firstStart > secondStart) && 
        (firstEnd === secondEnd) ) {
        return TimePeriodRelation.INSIDE_END_TOUCHING;
    }

    if ( (firstStart < secondStart) && 
        (firstEnd > secondStart) && 
        (firstEnd < secondEnd) ) {
        return TimePeriodRelation.END_INSIDE;
    }

    if ( (firstStart < secondStart) && 
        (firstEnd === secondStart) ) {
        return TimePeriodRelation.END_TOUCHING;
    }

    if ( (firstStart < secondStart) && 
        (firstEnd < secondStart) ) {
        return TimePeriodRelation.BEFORE;
    }

    return TimePeriodRelation.UNKNOWN;

}

function assertClashTypeEqual(expected, actual) {

    const result = (expected === actual) ? "PASS" : "FAIL";
    console.log(`${result} : ${expected}`);
    
}

function testClashTypes() {

    const testEventsMap = new Map();

    const testEvent = new TimePeriodEvent(
        new Date("05/01/2024 12:00"),
        new Date("05/01/2024 13:00")
    );

    testEventsMap.set("AFTER", new TimePeriodEvent(
        new Date("05/01/2024 11:00"),
        new Date("05/01/2024 11:30")
    ));

    testEventsMap.set("START_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 11:00"),
        new Date("05/01/2024 12:00")
    ));

    testEventsMap.set("START_INSIDE", new TimePeriodEvent(
        new Date("05/01/2024 11:30"),
        new Date("05/01/2024 12:30")
    ));

    testEventsMap.set("INSIDE_START_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 12:00"),
        new Date("05/01/2024 13:30")
    ));

    testEventsMap.set("ENCLOSING_START_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 12:00"),
        new Date("05/01/2024 12:30")
    ));

    testEventsMap.set("ENCLOSING", new TimePeriodEvent(
        new Date("05/01/2024 12:30"),
        new Date("05/01/2024 12:45")
    ));

    testEventsMap.set("ENCLOSING_END_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 12:30"),
        new Date("05/01/2024 13:00")
    ));

    testEventsMap.set("EXACT_MATCH", new TimePeriodEvent(
        new Date("05/01/2024 12:00"),
        new Date("05/01/2024 13:00")
    ));

    testEventsMap.set("INSIDE", new TimePeriodEvent(
        new Date("05/01/2024 11:30"),
        new Date("05/01/2024 13:30")
    ));

    testEventsMap.set("INSIDE_END_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 11:30"),
        new Date("05/01/2024 13:00")
    ));

    testEventsMap.set("END_INSIDE", new TimePeriodEvent(
        new Date("05/01/2024 12:30"),
        new Date("05/01/2024 13:30")
    ));

    testEventsMap.set("END_TOUCHING", new TimePeriodEvent(
        new Date("05/01/2024 13:00"),
        new Date("05/01/2024 13:30")
    ));

    testEventsMap.set("BEFORE", new TimePeriodEvent(
        new Date("05/01/2024 13:30"),
        new Date("05/01/2024 14:00")
    ));

    for (let [key, value] of testEventsMap) {
        assertClashTypeEqual(key, checkForClash(testEvent, value).toString());
    }

}