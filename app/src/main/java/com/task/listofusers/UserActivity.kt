package com.task.listofusers

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.task.listofusers.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var image: ShapeableImageView
    private lateinit var name: TextView
    private lateinit var birthday: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var location: TextView
    private lateinit var home: TextView
    private var _result: Result? = null
    private val result get() = _result!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        image = binding.circlePhotoUser
        name = binding.name
        birthday = binding.birthday
        email = binding.email
        phone = binding.phone
        location = binding.location
        home = binding.home

        val arguments = intent.extras
        _result = arguments?.getParcelable("user")

        Glide.with(this)
            .load(result.picture.medium)
            .into(image)
        name.text = getString(R.string.full_name, result.name.title, result.name.first, result.name.last)
        birthday.text = getString(R.string.full_birthday, result.dob.date.substringBeforeLast('T'), result.dob.age)
        email.text = createSpannableText(result.email)
        phone.text = createSpannableText(result.phone)
        location.text = getString(R.string.full_location, result.location.country, result.location.state, result.location.city)
        home.text = createSpannableText(getString(R.string.full_home, result.location.street.name, result.location.street.number))

        clickEmailTextView()
        clickLocationTextView()
        clickPhoneTextView()
        setContentView(binding.root)
    }

    private fun createSpannableText(text: String): SpannableString{
        val spannableString = SpannableString(text)
        val colorSpan = ForegroundColorSpan(Color.BLUE)
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(colorSpan, 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
    private fun clickEmailTextView() {
        email.setOnClickListener {
            val uri = Uri.parse("mailto:${email.text}")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            startActivity(intent)
        }
    }

    private fun clickLocationTextView() {
        home.setOnClickListener {
            val geo =
                Uri.parse("geo:${result.location.coordinates.latitude},${result.location.coordinates.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, geo)
            startActivity(intent)
        }
    }

    private fun clickPhoneTextView() {
        phone.setOnClickListener {
            val call = Uri.parse("tel:${phone.text}")
            val intent = Intent(Intent.ACTION_DIAL, call)
            startActivity(intent)
        }
    }
}