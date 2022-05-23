package pm.miyashiro.team.cineluna.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import pm.miyashiro.team.cineluna.R
import androidx.fragment.app.FragmentTransaction
import pm.miyashiro.team.cineluna.classes.controller.DatosUsuario
import pm.miyashiro.team.cineluna.databinding.FargmentSobrenosotrosBinding

class SobreNosotrosFragment : Fragment() {
    private var _binding : FargmentSobrenosotrosBinding? = null
    private val binding get() = _binding!!
    private var navmain : NavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this , object:OnBackPressedCallback(true){
            override fun handleOnBackPressed(){
                navmain = activity?.findViewById(R.id.navMain)
                navmain?.setCheckedItem(R.id.pelis)
                parentFragmentManager.popBackStack()
                (activity as AppCompatActivity).supportActionBar?.title = "Hola " + DatosUsuario.NombreUsuario + "!"

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FargmentSobrenosotrosBinding.inflate(inflater, container, false)
        binding.butRegresar.setOnClickListener {
            parentFragmentManager.popBackStack()
            navmain = activity?.findViewById(R.id.navMain)
            navmain?.setCheckedItem(R.id.pelis)
            (activity as AppCompatActivity).supportActionBar?.title = "Hola " + DatosUsuario.NombreUsuario + "!"
        }
        return binding.root
    }
}