/*
    Form with if-else statements, conditionally declared questions, unary NOT operator
*/

form ifElse {
    "Show section"
        showBlock: boolean = false
    if (showBlock) {
        "Block is shown"
        flag: boolean = true
    } else {
        "Block is hidden"
        flag2: boolean = true
    }
}