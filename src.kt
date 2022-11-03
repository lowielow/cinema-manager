package cinema

class Cinema(val rowNum: Int, val seatNum: Int) {

    var seating = MutableList(rowNum) { MutableList(seatNum){ "S" } }  
    var ticketPurchased: Int = 0   
    var ticketPercentage: Double = 0.0       
    var currentIncome: Int = 0
    val totalIncome: Int = if (rowNum * seatNum <= 60) {
        rowNum * seatNum * 10
    } else {
        rowNum / 2 * seatNum * 10 + (rowNum - rowNum / 2) * seatNum * 8
    }
        
    fun printStatistics() {
        println("\nNumber of purchased tickets: $ticketPurchased")
        println("Percentage: ${"%.2f".format(ticketPercentage)}%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
    }
    
    fun printActionMenu() {
        println("\n1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
    }
    
    fun printSeatings() {
        
        val seatIndex = mutableListOf<String>()
        var rowIndex: Int = 1
        
        for (i in 1..seating[0].size) {
            seatIndex.add(i.toString())    
        }

        println("\nCinema:")
        println("  ${seatIndex.joinToString(" ")}")
 
        for (i in 0..seating.size - 1) {
            println("$rowIndex ${seating[i].joinToString(" ")}")
            rowIndex++
        }        
    }

    fun calculateTicketPrice(inputRow: Int): Int {
        
        var ticketPrice: Int = if (rowNum * seatNum <= 60 || inputRow <= rowNum / 2) {
            return 10
        } else {
            return 8
        }
        
    }
    
    fun handlePurchaseInput(inputRow: Int, inputSeat: Int) {
        
            seating[inputRow - 1][inputSeat - 1] = "B"
            ticketPurchased++
            currentIncome += calculateTicketPrice(inputRow)
            ticketPercentage = ticketPurchased.toDouble() / (rowNum * seatNum) * 100
            println("\nTicket price: $${calculateTicketPrice(inputRow)}")
                 
    }
}

fun main() {
    
    println("Enter the number of rows:")
    val rowNum: Int = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatNum: Int = readln().toInt()
    val cinema = Cinema(rowNum, seatNum)
    
    while (true) {
        cinema.printActionMenu()
        val input = readln().toInt()
        when (input) {
            1 -> cinema.printSeatings()    
            2 -> {
                while (true) {
                    println("\nEnter a row number:")
                    val inputRow: Int = readln().toInt()
                    println("Enter a seat number in that row:")
                    val inputSeat: Int = readln().toInt()
                    if (inputRow > rowNum || inputSeat > seatNum) {
                        println("Wrong input!")
                    } else if (cinema.seating[inputRow - 1][inputSeat - 1] == "B") {
                        println("That ticket has already been purchased!")
                    } else {
                    	cinema.handlePurchaseInput(inputRow, inputSeat)
                        break
                    }
                }
            }
            3 -> cinema.printStatistics()
            0 -> break
        }
    }
    
}
