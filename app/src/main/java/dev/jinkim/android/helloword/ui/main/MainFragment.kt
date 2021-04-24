package dev.jinkim.android.helloword.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val definitionAdapter = WordDefinitionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView and Adapter
        binding.wordDefinitionList.apply {
            this.adapter = definitionAdapter
            val llm = LinearLayoutManager(view.context)
            llm.orientation = LinearLayoutManager.VERTICAL
            layoutManager = llm
        }
        val today = MaterialDatePicker.todayInUtcMilliseconds()

        viewModel.selectedDateString.observe(viewLifecycleOwner) { dateString ->
            binding.title.text = viewModel.getTitleString(view.context, dateString)
            binding.word.text = getString(R.string.hw_loading)
            viewModel.getWordOfTheDay(dateString)
                .observe(viewLifecycleOwner) { wordOfTheDay ->
                    binding.word.text = wordOfTheDay.word
                    definitionAdapter.setItems(wordOfTheDay.definitions)
                }
        }

        binding.selectDateBtn.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(
                    CalendarConstraints.Builder().setValidator(
                        DateValidatorPointBackward.before(today)
                    ).build()
                )
                .setTitleText(getString(R.string.hw_date_picker_header))
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
