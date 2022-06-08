package com.example.branchedlauncher.ui.leadscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.branchedlauncher.databinding.FragmentLeadBinding
import com.example.branchedlauncher.model.App
import com.example.branchedlauncher.ui.animation.AnimationPattern
import com.example.branchedlauncher.ui.animation.ClockwiseViewAnimator
import com.example.branchedlauncher.ui.animation.ViewAnimator
import com.example.branchedlauncher.ui.widgets.AppView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LeadScreenFragment : Fragment() {

    private var _binding: FragmentLeadBinding? = null
    private val viewModel: LeadScreenViewModel by viewModels()
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var touchListener: View.OnTouchListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val appsLayouts = attachAppView()
        val appsLayouts = attachAppView()

        val clockwiseWiseAnimator = ClockwiseViewAnimator()

        navController = findNavController()
        touchListener = LeadScreenSwipeListener(requireContext(), navController)

        startAppViewAnimation(appsLayouts, clockwiseWiseAnimator)

        binding.leadLayout.setOnTouchListener(touchListener)
        loadApps()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun loadApps(): MutableList<App> = viewModel.loadApps()

    private fun loadTestApps(): Map<String, Int> = viewModel.loadTestApps()

    private fun attachAppView(): List<LinearLayout> {
        val appView = AppView(requireContext())
        val testApps = loadApps()
        val appsLayouts = mutableListOf<LinearLayout>()

        for (x in testApps) {
            val appLayout = appView.createAppLayout()
            binding.leadLayout.addView(appLayout)

            val appName = appView.createAppName(x.name)
            val appIcon = appView.createAppIcon(x.icon)
            val appItem = appView.attachViewsToAppLayout(appLayout, appIcon, appName)
            appsLayouts += appItem

            appIcon.setOnClickListener {
                Log.d("onTouched", appIcon.toString())
                val intent = appName.context.packageManager.getLaunchIntentForPackage(
                        x.packageName
                    )
                this.startActivity(intent);
            }
        }

        return appsLayouts
    }

    private fun attachAppViewTestData(): List<LinearLayout> {
        val appView = AppView(requireContext())
        val testApps = loadTestApps()
        val appsLayouts = mutableListOf<LinearLayout>()

        for (x in testApps) {
            val appLayout = appView.createAppLayout()
            binding.leadLayout.addView(appLayout)

            val appName = appView.createAppName(x.key)
            val appIcon = appView.createAppIcon(x.value)
            val tt = appView.attachViewsToAppLayout(appLayout, appIcon, appName)
            appsLayouts += tt

            appIcon.setOnClickListener {
                Log.d("onTouched", appIcon.toString())
            }
        }

        return appsLayouts
    }

    private fun startAppViewAnimation(
        appsLayouts: List<LinearLayout>,
        viewAnimator: ViewAnimator
    ) {
        val animationPattern = AnimationPattern()
            .defineAnimationPattern(viewAnimator, appsLayouts.size)

        for (x in 0 until appsLayouts.size) {
            val animator = viewAnimator.constructAnimation(
                view = appsLayouts[x],
                left = animationPattern[x]["left"]!!,
                top = animationPattern[x]["top"]!!,
                right = animationPattern[x]["right"]!!,
                bottom = animationPattern[x]["bottom"]!!,
                startAngle = animationPattern[x]["startAngle"]!!,
                sweepAngle = animationPattern[x]["sweepAngle"]!!
            )

            viewAnimator.startAnimation(
                animator,
                20000L,
                22
            )

        }

    }

}



