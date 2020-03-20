package parking

import java.util.Scanner

class Car(val number: String, val color: String) {
    override fun toString(): String {
        return "$number $color"
    }
}

class ParkingLot(lotSize: Int) {

    init {
        println("Created a parking lot with $lotSize spots.")
    }

    private val spots = Array<Car?>(lotSize) { null }

    fun printStatus() {
        var n = 0
        for (i in spots.indices) {
            val spot = spots[i]
            if (spot != null) {
                println("${i + 1} $spot")
                n += 1
            }
        }
        if (n == 0) println("Parking lot is empty.")
    }

    fun parkCar(car: Car) {
        val spotNumber = getLastFreeSpot()
        if (spotNumber > 0) {
            spots[spotNumber - 1] = car
            println("${car.color} car parked on the spot $spotNumber.")
        } else {
            println("Sorry, parking lot is full.")
        }
    }

    fun leaveCar(spotNumber: Int) {
        if (spots[spotNumber - 1] != null) {
            println("Spot $spotNumber is free.")
            spots[spotNumber - 1] = null
        } else {
            println("There is no car in the spot $spotNumber.")
        }
    }

    fun printSpotsByColor(color: String) {
        var found = false
        for (i in spots.indices) {
            val spot = spots[i]
            if (spot != null && spot.color.equals(color, true)) {
                print(if (found) ", ${i + 1}" else "${i + 1}")
                found = true
            }
        }
        println(if (found) "" else "No cars with color $color were found.")
    }

    fun printSpotByNumber(number: String) {
        var found = false
        for (i in spots.indices) {
            val spot = spots[i]
            if (spot != null && spot.number == number) {
                println(i + 1)
                found = true
                break
            }
        }
        if (!found) println("No cars with registration number $number were found.")
    }

    fun printNumbersByColor(color: String) {
        var found = false
        for (spot in spots) {
            if (spot != null && spot.color.equals(color, true)) {
                print(if (found) ", ${spot.number}" else spot.number)
                found = true
            }
        }
        println(if (found) "" else "No cars with color $color were found.")
    }

    private fun getLastFreeSpot(): Int {
        for (i in spots.indices) {
            if (spots[i] == null) return i + 1
        }
        return 0
    }

}

fun main() {
    val scanner = Scanner(System.`in`)
    var park: ParkingLot? = null
    var command = scanner.next()
    while (command != "exit") {
        when (command) {
            "create" -> {
                park = ParkingLot(scanner.nextInt())
            }
            "status" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.printStatus()
            }
            "park" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.parkCar( Car(scanner.next(), scanner.next()) )
            }
            "leave" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.leaveCar( scanner.nextInt() )
            }
            "spot_by_color" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.printSpotsByColor( scanner.next() )
            }
            "spot_by_reg" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.printSpotByNumber( scanner.next() )
            }
            "reg_by_color" -> {
                if (park == null) println("Sorry, parking lot is not created.")
                else park.printNumbersByColor( scanner.next() )
            }
        }
        command = scanner.next()
    }
}
