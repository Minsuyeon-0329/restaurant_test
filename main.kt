class Owner(_name: String, _money: Int) {
    var name: String = _name
    var money: Int = _money

    fun paySalary(salary: Int) {
        money -= salary
        println("[Owner] ${this.name}: paid each workers a salary of ${salary/2}")
    }

    fun getMoney(foodPrice: Int) {
        money += foodPrice
    }

    fun showInfo() {
        println("[Owner] ${this.name}: has ${this.money}won")
    }
}

open class Worker(_name: String) {
    var name: String = _name
}

class Cook(_name: String):Worker(_name) {
    var complete: Int =0

    fun passFood(server : Server) {
        if (complete != 0) {
            server.serving()
        }else{
            println("No Food Ready!!")
        }
        println("[Cook] ${this.name}: passes the food")
    }

    fun makeFood(menuList: MutableList<String>) {
        complete += 1
        println("[Cook] ${this.name}: made all the food")
    }
}

class Server(_name: String):Worker(_name) {
    var totalFoodPrice: Int = 0
    var menuList : MutableList<String> = mutableListOf<String>()

    fun getMenu(orderMenu: MutableList<String>) {
        menuList = orderMenu
        println("[Server] ${this.name}: gets the menu")
    }

    fun ordertoCook(cook : Cook) {
        println("[Server] ${this.name}: makes cook food")
        cook.makeFood(menuList)
    }

    fun serving() {
        println("[Server] ${this.name}: serves the food")
    }

    fun getFoodPrice(price: Int) {
        totalFoodPrice += price
    }

    fun passMoney(owner : Owner) {
        owner.getMoney(totalFoodPrice)
        println("[Server] ${this.name}: passes ${this.totalFoodPrice}won to Owner")
    }
}

class Menu(_name: String, _price: Int) {
    var name: String = _name
    var price: Int = _price
}

class Customer(_name: String) {
    var name: String = _name
    var bill: Int = 0
    var orderMenu : MutableList<String> = mutableListOf<String>()

    fun order(menu : Menu) {
        this.orderMenu.add(menu.name) 
        bill += menu.price
    }

    fun showOrder(server : Server) {
        println("[Customer] ${this.name}: orders ${this.orderMenu}}")
        server.getMenu(orderMenu)
    }

    fun payforFood(server : Server) {
        server.getFoodPrice(bill)
        println("[Customer] ${this.name}: pays ${this.bill}won")
    }
}

fun main() {
    val owner = Owner("owner",1000000)
    val cook = Cook("cook")
    val server = Server("server")
    val customer1 = Customer("customer1")
    val customer2 = Customer("customer2")
    val customer3 = Customer("customer3")

    val set = Menu("Set Meal", 5000)
    val special = Menu("Special", 7000)
    val coke = Menu("Coke", 2000)
    val cider = Menu("Cider", 2000)

    customer1.order(set);
    customer1.order(cider);
    customer1.showOrder(server);
    server.ordertoCook(cook);
    cook.passFood(server);
    customer1.payforFood(server);

    customer2.order(special);
    customer2.order(coke);
    customer2.showOrder(server);
    server.ordertoCook(cook);
    cook.passFood(server);
    customer3.order(special);
    customer3.order(coke);
    customer3.showOrder(server);
    server.ordertoCook(cook);
    cook.passFood(server);
    customer2.payforFood(server);
    customer3.payforFood(server);
    
    server.passMoney(owner)
    
    owner.paySalary(100000)
    owner.showInfo()
}
