package pm.miyashiro.team.cineluna.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import pm.miyashiro.team.cineluna.databinding.FargmentSobrenosotrosBinding

class SobreNosotrosFragment : Fragment() {
    private var _binding : FargmentSobrenosotrosBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FargmentSobrenosotrosBinding.inflate(inflater, container, false)
        binding.butRegresar.setOnClickListener {
            val transaction : FragmentTransaction = requireFragmentManager().beginTransaction()

            transaction.remove(this).commit()
        }
        return binding.root
    }
}