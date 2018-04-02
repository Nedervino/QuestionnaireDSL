/*
    Form with nested if-else statements
*/

form ifElse {
    ""
        flag1: boolean = false
    if (flag1) {
        ""
        flag2: boolean = false
    } else {
        ""
        flag2: boolean = true
    }
    if (flag2) {
        ""
        flag3: boolean = false
        if (!flag3) {
            ""
            result: money = 10.00
        }
    }
}