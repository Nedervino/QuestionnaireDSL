/*
    Form with if-else statements, nested conditional questions, unary NOT operator
*/

form ifElse {
    "Show section"
        showBlock: boolean = false
    if (showBlock) {
        "Block is shown"
        flag: boolean = false
    } else {
        "Block is hidden"
        flag: boolean = true
    }
}