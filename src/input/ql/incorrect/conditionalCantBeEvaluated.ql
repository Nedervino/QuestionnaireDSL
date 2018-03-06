/*
    Form with conditional that can never be evaluated
*/

form taxOfficeExample {
    if(hasSoldHouse) {
        "Did you sell a house in 2010?"
            hasSoldHouse: boolean
    }
}