package space.ten.currencyconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var currencySP = mutableListOf<String>()
    private var currency = mutableListOf<MainCurrency>()
    var type=""

    private lateinit var number: EditText
    private lateinit var sp1: Spinner
    private lateinit var sp2: Spinner
    private lateinit var rate: TextView
    private lateinit var result: TextView
    private lateinit var calc: Button
    private lateinit var radioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        СreateListCurrencies()

        number = findViewById(R.id.number)
        rate = findViewById(R.id.rate)
        result = findViewById(R.id.result)
        calc = findViewById(R.id.calc)

        radioGroup = findViewById(R.id.select)
        radioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->
            when (checkedId) {
                R.id.cb -> type = "cb"
                R.id.buy -> type = "buy"
                R.id.sale -> type = "sale"
            }
        }

        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);

        //Упростить адаптер, читать в него сразу из списка валют их наименования
        // Создаем адаптер ArrayAdapter с помощью стандартной разметки элемета spinner
        val adapter = ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, currencySP);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sp1.adapter = adapter;
        sp2.adapter = adapter;
        sp1.onItemSelectedListener = this;
        sp2.onItemSelectedListener = this;
    }

    public fun onButtonClicked(view:View){
        val value = Integer.parseInt(number.getText().toString())
        val currency1 = currency[sp1.getSelectedItemPosition()]
        val currency2 = currency[sp2.getSelectedItemPosition()]

        if (type!="") {
            val rateK = currency1.Conversion(currency2, type);
            val res = value * rateK;
            rate.text = rateK.toString()
            result.text = String.format("%.2f",res);
        }

    }

    private fun СreateListCurrencies (){
        currency.add(MainCurrency("RUB", "Рубль", 1, 1F, 1F, 1F))
        currency.add(MainCurrency("USD", "Доллар США", 1, 61.55F, 61.75F, 61.25F))
        currency.add(MainCurrency("EUR", "Евро", 1, 60.55F, 60.75F, 60.25F))
        currency.add(MainCurrency("CNY", "Китайских юаней", 10, 84.28F, 86.28F, 82.28F))
        currency.add( MainCurrency("jPY", "Японских иен", 100, 41.50F, 41.70F, 41.30F))
        currency.add(MainCurrency("KZT", "Казахстанских тенге", 100, 13.35F, 13.50F, 13.20F))
        currency.add(MainCurrency("TRY", "Турецких лир", 10, 33.30F, 33.50F, 33.20F))
        for (c in currency) currencySP.add(c.Name);
    }

    override fun onItemSelected(parent:AdapterView<*>, view:View, position:Int, id:Long) {
        // Получаем выбранный объект
        var item = parent.getItemAtPosition(position)
    }
    override fun onNothingSelected(parent:AdapterView<*>) {
    }

}

//размр экрана - спинер не влезает
//поворот экрана
// показывать курс обмена до рассчета
// валидация -запретить ввод числа, кроме цифр и перевода строки и тд