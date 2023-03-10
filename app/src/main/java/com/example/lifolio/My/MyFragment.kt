package com.example.lifolio.My

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.BnbActivity
import com.example.lifolio.R
import com.example.lifolio.Record.RecordActivity
import com.example.lifolio.ViewAllMyLifolio.ViewAllLifolioActivity
import com.example.lifolio.databinding.FragmentMyBinding

class MyFragment : Fragment() {
    private lateinit var binding: FragmentMyBinding
    private lateinit var bnbActivity: BnbActivity

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): MyFragment {
            return MyFragment()
        }
    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MyFragment - onCreate() called")
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "MyFragment - onAttach() called")
        bnbActivity = context as BnbActivity // Context를 부모 액티비티로 형변환해서 할당, context 필요시 사용
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentMyBinding.inflate(layoutInflater)
        binding.profileBtn.setOnClickListener {
            bnbActivity.changeMyFragment(MyFragment.newInstance())
        }

        // 챠코 기능 추가
        binding.btnGotoMylifolio.setOnClickListener {
            val intent = Intent(requireContext(), ViewAllLifolioActivity::class.java)
            startActivity(intent)
        }
        binding.fabRecord.setOnClickListener {
            val intent = Intent(requireContext(), RecordActivity::class.java)
            startActivity(intent)
        }
        return binding.root
//
//        Log.d(TAG, "HomeFragment - onCreateView() called")
//        val view = inflater.inflate(R.layout.fragment_my, container, false)
//        return view

    }

}