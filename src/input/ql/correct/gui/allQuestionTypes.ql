/*
    Include all question types
*/

form taxOfficeExample {
    "Boolean?"
        q1: boolean
    "String?"
        q2: string
    "Decimal?"
        q3: decimal
    "Integer?"
        q4: integer
    "String?"
        q5: money
    "Decimal?"
        q6: date
    "Generated error type"
        q7: integer = 1/0

}