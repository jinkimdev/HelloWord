package dev.jinkim.android.helloword.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dev.jinkim.android.helloword.R
import dev.jinkim.android.helloword.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import java.text.SimpleDateFormat
import java.util.*

val fragmentModule = module {
    factory { MainFragment() }
}

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel.wordOfTheDay.observe(viewLifecycleOwner) {
            binding.message.text = it.toString()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val today = MaterialDatePicker.todayInUtcMilliseconds()

        viewModel.selectedDateString.observe(viewLifecycleOwner) {
            binding.title.text = viewModel.getTitleString(view.context, it)
        }

        binding.selectDateBtn.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(
                    CalendarConstraints.Builder().setValidator(
                        DateValidatorPointBackward.before(today)
                    ).build()
                )
                .setTitleText("Select date for word of the day")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            datePicker.addOnPositiveButtonClickListener {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                val date = sdf.format(Date(it))
                Log.d("Date", date)

                viewModel.selectedDateString.postValue(date)
            }
            datePicker.show(parentFragmentManager, "MainFragment")
        }
    }
}
