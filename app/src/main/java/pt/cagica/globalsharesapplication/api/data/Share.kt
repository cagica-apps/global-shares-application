package pt.cagica.globalsharesapplication.api.data

import com.google.gson.annotations.SerializedName

class Share(name: String, symbol: String, value: Double) {

    @SerializedName("name")
    private var name: String = ""

    @SerializedName("symbol")
    private var symbol: String = ""

    @SerializedName("value")
    private var value: Double = 0.0

    init {
        this.name = name
        this.symbol = symbol
        this.value = value
    }

    fun getName(): String {
        return name
    }

    fun getSymbol(): String {
        return symbol
    }

    fun getValue(): Double {
        return value
    }
}
