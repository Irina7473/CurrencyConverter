package space.ten.currencyconverter

class MainCurrency (val CharCode:String, val Name:String,
                    val Nominal:Int, val CB:Float, val Buy:Float, val Sale:Float){

    fun Conversion (currency:MainCurrency, type:String):Float{
        var rate = 1F
        when(type) {
            "cb"  -> rate=(this.CB/this.Nominal)/(currency.CB/ currency.Nominal)
            "buy" -> rate=(this.Buy/this.Nominal)/(currency.Sale/ currency.Nominal)
            "sale"-> rate=(this.Sale/this.Nominal)/(currency.Buy/ currency.Nominal)
        }
        return  rate;
    }

}