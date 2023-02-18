package com.example.myfridge

class GList {
    var id : Int = 0
    var gName : String = ""
    var website : String = ""
    var noOfItems : Int = 0
    var expiryDate : String = ""

    constructor(gName:String,website:String,noOfItems:Int,expiryDate:String){
        this.gName = gName
        this.website = website
        this.noOfItems = noOfItems
        this.expiryDate = expiryDate


    }
    constructor(){

    }
}