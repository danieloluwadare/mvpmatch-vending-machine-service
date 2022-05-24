package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.utils.OrderUtils
import org.junit.jupiter.api.Test
import java.util.StringJoiner
import java.util.stream.Collectors

class OrderServiceImplTest{

    @Test
    fun `testmutablemap` (){
        val myMap = mutableMapOf <Int,Int>()
        myMap[3] = 4
        myMap[1] = 2
        myMap[2] = 3

        myMap.keys.forEach{key->
            val v = myMap[key]!! - 1
            myMap[key] = v
        }

        print(myMap)
    }

    @Test
    fun `test_change`(){
        val list = ArrayList<CoinFrequency>()

        var coinFrequency = CoinFrequency()
        coinFrequency.value = 100
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 50
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 20
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 10
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 5
        coinFrequency.frequency=0
        list.add(coinFrequency)

//        val coins = OrderUtils.calcualte(350, list)
//        val sb = StringJoiner(",")
//        coins.forEach { coin->sb.add(coin.toString()) }
//        println("sb $sb")
//        println("change ==> $coins")
//
        val lsit ="1,2,3".split(',').stream().map { coin->Integer.parseInt(coin) }.collect(
            Collectors.toList())
        println("lsit ==> $lsit")

    }

    private fun calculateChange(change : Int, coinsAvailable : List<CoinFrequency>) : List<Int>{
        val listOfChange = ArrayList<Int>()
        var userChange = change
        var iterator = 0
        var sum = 0
        while(userChange != 0 && iterator < coinsAvailable.size){
            val highestDenomination  = coinsAvailable[iterator]
            if(highestDenomination.value > userChange || highestDenomination.frequency == 0){
                iterator ++
                continue
            }
            sum += highestDenomination.value
            listOfChange.add(highestDenomination.value)
            userChange -= highestDenomination.value
            highestDenomination.frequency--

//            if(highestDenomination.frequency > 0){
//                sum += highestDenomination.value
//                listOfChange.add(highestDenomination.value)
//                userChange -= highestDenomination.value
//                highestDenomination.frequency--
//            }
//            if(highestDenomination.frequency == 0) iterator++
        }
        println("sum ==> $sum")
        println("userChange ==> $userChange")
        return listOfChange
    }

}