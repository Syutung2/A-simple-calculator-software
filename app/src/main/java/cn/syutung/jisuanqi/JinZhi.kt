package cn.syutung.jisuanqi

import android.annotation.SuppressLint
import android.app.Service
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_jin_zhi.*
import kotlinx.android.synthetic.main.activity_jin_zhi.about3
import kotlinx.android.synthetic.main.activity_jin_zhi.more3


class JinZhi : AppCompatActivity() {
    protected var useThemestatusBarColor = false
    protected var useStatusBarColor = true
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val mySysTheme =
            newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when(mySysTheme){
            Configuration.UI_MODE_NIGHT_YES ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            Configuration.UI_MODE_NIGHT_NO ->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        recreate()
    }
    private fun layoutBackgroundBlur(context: Context?, i : Int){
        val layout : RelativeLayout =findViewById<RelativeLayout>(i)
        val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
        val wallpaperDrawable: Drawable = wallpaperManager.drawable
        val bd = wallpaperDrawable as BitmapDrawable
        val bm = bd.bitmap
        val drawable: Drawable =   BitmapDrawable(resources, Tools.blurBitmap(context, bm, 12F))
        layout.background = drawable
    }


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jin_zhi)
        layoutBackgroundBlur(this,R.id.jinzhi)
        if (Build.VERSION.SDK_INT>=21){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }

        qingkong.setOnClickListener {
            

            shijinzhi.setText("")
            bajinzhi.setText("")
            erjinzhi.setText("")
            shiliujinzhi.setText("")
            bajinzhi.setEnabled(true)
            erjinzhi.setEnabled(true)
            shijinzhi.setEnabled(true)
            shiliujinzhi.setEnabled(true)
        }
        about3.setOnClickListener {
            val intant = Intent(this, About::class.java)
            startActivity(intant)
        }
        more3.setOnClickListener {
            val intant = Intent(this, More::class.java)
            startActivity(intant)
        }
        shiliujinzhi.addTextChangedListener(  object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                bajinzhi.setEnabled(false)
                erjinzhi.setEnabled(false)
                shijinzhi.setEnabled(false)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        bajinzhi.addTextChangedListener(  object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                shiliujinzhi.setEnabled(false)
                erjinzhi.setEnabled(false)
                shijinzhi.setEnabled(false)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        erjinzhi.addTextChangedListener(  object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                bajinzhi.setEnabled(false)
                shiliujinzhi.setEnabled(false)
                shijinzhi.setEnabled(false)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        shijinzhi.addTextChangedListener(  object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                bajinzhi.setEnabled(false)
                erjinzhi.setEnabled(false)
                shiliujinzhi.setEnabled(false)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        quren.setOnClickListener {
            

            val bajinzhi1 = bajinzhi.text.toString()
            val erjinzhi1 = erjinzhi.text.toString()
            val shiliujinzhi1 =shiliujinzhi.text.toString()
            val shijinzhi1 =shijinzhi.text.toString()
            if (shijinzhi.text.toString()!="" && bajinzhi1 =="" && shiliujinzhi1==""  &&erjinzhi1==""){
                val shi =shijinzhi.text.toString().toInt()
                bajinzhi.setText(toEight(shi))
                erjinzhi.setText(toEr(shi))
                shiliujinzhi.setText(toShiLiu(shi))
            }
            else if(bajinzhi.text.toString()!="" && shijinzhi1=="" && erjinzhi1=="" && shiliujinzhi1==""){
                val numinput  = bajinzhi.text.toString()
                var s = 0
                for (i in 0 until  numinput.length){
                        if (numinput[i] in  '0'..'7' ){
                            s+=0
                        }
                        else{
                            s+=1
                        }


                }

                if (s == 0){
                    val shi = ba_to_10(numinput.toInt()).toInt()
                    shijinzhi.setText(shi.toString())
                    erjinzhi.setText(toEr(shi))
                    shiliujinzhi.setText(toShiLiu(shi))
                }
                else{
                    Toast.makeText(this,"您的八进制数输入错误" +
                            "", Toast.LENGTH_SHORT).show()
                }


            }
            else if(erjinzhi.text.toString()!="" && shijinzhi1=="" && bajinzhi1=="" && shiliujinzhi1==""){
                val numinput  = erjinzhi.text.toString()
                var s = 0
                for (i in 0 until  numinput.length){
                    if (numinput[i] in '0'..'1' ){
                        s+=0
                    }
                    else{
                        s+=1
                    }
                }
                if (s != 0){
                    Toast.makeText(this,"您的二进制数输入错误" +
                            "", Toast.LENGTH_SHORT).show()
                }
                else{
                    val shi = er_to_10(numinput.toInt())
                    shijinzhi.setText(shi.toString())
                    bajinzhi.setText(toEight(shi.toInt()))
                    shiliujinzhi.setText(toShiLiu(shi.toInt()))
                }


            }
            else if(shiliujinzhi.text.toString()!="" && shijinzhi1=="" && erjinzhi1=="" && erjinzhi1=="" ){
                val numinput  = shiliujinzhi.text.toString()
                var s = 0
                for (i in 0 until  numinput.length){
                    if (numinput[i] in '0'..'9' || numinput[i] in 'A'..'F' ){
                        s+=0
                    }
                    else{
                        s+=1
                    }
                }
                if (s != 0){
                    Toast.makeText(this,"您的十六进制数输入错误" +
                            "", Toast.LENGTH_SHORT).show()
                }
                else{
                    val shi = shiliu_to_10(numinput).toInt()
                    shijinzhi.setText(shi.toString())
                    bajinzhi.setText(toEight(shi))
                    erjinzhi.setText(toEr(shi))
                }




            }
            else{
                Toast.makeText(this,"输入错误" +
                        "", Toast.LENGTH_SHORT).show()
            }
        }
    }





    fun toEight(num:Int): String {
        var l=num
        var m= ""
        var p=""
        while (l != 0) {
            var k = l % 8
            m = m + k.toString()
            l = l / 8
        }
        for (i in 0 until m.length){
            p=p+m[m.length-1-i]
        }
        return p

    }
    fun toEr(num:Int): String {
        var l=num
        var m= ""
        var p =""
        while (l != 0) {
            var k = l % 2
            m = m + k.toString()
            l = l / 2
        }
        for (i in 0 until m.length){
            p=p+m[m.length-1-i]
        }
        return p

    }
    fun toShiLiu(num: Int): String {
        val lm = arrayOf("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F")
        var l=num
        var m =""
        var p=""
        while (l != 0) {
            var k = l % 16
            m = m + lm[k]
            l = l / 16
        }
        for (i in 0 until m.length){
            p=p+m[m.length-1-i]
        }
        return p
    }

    fun er_to_10(num: Int):String{
        var t=num
        var s=0
        var long =  t.toString().length
        for (i in 0 until long){
            if (i==0){
                s+=(t%10)
                t=t/10
            }
            else{
                s+=(t%10)*cifang(2,i)
                t=t/10
            }
        }
        return s.toString()
    }

    fun ba_to_10(num:Int):String{
        var t=num
        var s=0
        var long =  t.toString().length
        for (i in 0 until long){
            if (i==0){
                s+=(t%10)
                t=t/10
            }
            else{
                s+=(t%10)*cifang(8,i)
                t=t/10
            }
        }
        return s.toString()
    }

    fun shiliu_to_10(num:String):String{

        return num.toInt(16).toString()
    }

    fun cifang(num:Int,a: Int) :Int {
       return Math.pow(num.toDouble(), a.toDouble()).toInt()
    }

}



