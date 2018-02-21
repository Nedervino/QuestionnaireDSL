/*
    Form with if-else statements, nested conditional questions, unary NOT operator
*/

form ifElse {
    "Show section 1?" showBlock: boolean
    "Hiding first section:" blockShown: boolean = (!showBlock)
    if (showBlock) {
        "Show section 1.1?" showNestedBlock: boolean
        if (showNestedBlock) {
            "Show section 1.1.1?" showDoubleNestedBlock: boolean
            if (showDoubleNestedBlock || showDoubleNestedBlockRetry) {
                "Reached final section:" finalSection: boolean = true
            } else {
                "Are you sure?" showDoubleNestedBlockRetry: boolean
            }
        } else {
            "Hiding second section:" showingSecondSection: boolean = (!showNestedBlock)
        }
    }
    "Showing all blocks:" showingAll: boolean = (showBlock && showNestedBlock && showDoubleNestedBlock)
}