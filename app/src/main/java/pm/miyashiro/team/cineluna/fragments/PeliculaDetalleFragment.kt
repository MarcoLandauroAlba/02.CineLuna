package pm.miyashiro.team.cineluna.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pm.miyashiro.team.cineluna.R
import pm.miyashiro.team.cineluna.databinding.FragmentDetallepeliculaBinding

class PeliculaDetalleFragment : Fragment(){
    private var _binding : FragmentDetallepeliculaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallepeliculaBinding.inflate(inflater,container,false)
        return binding.root
    }
}