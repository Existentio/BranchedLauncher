package com.example.branchedlauncher.ui.leadscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.branchedlauncher.databinding.FragmentLeadBinding
import com.example.branchedlauncher.model.App
import com.example.branchedlauncher.services.NotificationService
import com.example.branchedlauncher.ui.animation.AnimationPattern
import com.example.branchedlauncher.ui.animation.ClockwiseViewAnimator
import com.example.branchedlauncher.ui.animation.ViewAnimator
import com.example.branchedlauncher.ui.widgets.AppView
import com.example.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_ICON_HEIGHT_MEDIUM
import com.example.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_ICON_WIDTH_MEDIUM
import com.example.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_LAYOUT_HEIGHT
import com.example.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_LAYOUT_WIDTH
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appsLayouts = attachAppView()

        val clockwiseWiseAnimator = ClockwiseViewAnimator()

        navController = findNavController()
        touchListener = LeadScreenSwipeListener(requireContext(), navController)
        binding.leadLayout.setOnTouchListener(touchListener)

        startAppViewAnimation(appsLayouts, clockwiseWiseAnimator)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun loadApps(): MutableList<App> = viewModel.loadApps()

    private fun loadTestApps(): Map<String, Int> = viewModel.loadTestApps()

    private fun attachAppView(): List<LinearLayout> {
        val appView = AppView(requireContext())
        val apps = loadApps()
        val appsLayouts = mutableListOf<LinearLayout>()

        for (x in apps) {
            val appLayout = appView.createAppLayout()
            binding.leadLayout.addView(appLayout)
            val appName = appView.createAppName(x.name)
            val appIcon = appView.createAppIcon(x.icon)

            val appItem = appView.attachViewsToAppLayout(appLayout, appIcon, appName)

            appItem.layoutParams.width = APP_LAYOUT_WIDTH
            appItem.layoutParams.height = APP_LAYOUT_HEIGHT
            appItem.foregroundGravity = Gravity.CENTER_HORIZONTAL
            appItem.orientation = LinearLayout.VERTICAL

            appIcon.layoutParams.width = APP_ICON_WIDTH_MEDIUM
            appIcon.layoutParams.height = APP_ICON_HEIGHT_MEDIUM
            appIcon.foregroundGravity = Gravity.CENTER

            appName.foregroundGravity = Gravity.CENTER_HORIZONTAL
            appName.maxEms = 6
            appName.maxLines = 2

            appsLayouts += appItem

            appIcon.setOnClickListener {
                Log.d("onTouched", x.name)
                val intent = appName.context.packageManager.getLaunchIntentForPackage(
                    x.packageName
                )
                this.startActivity(intent)
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
            val appItem = appView.attachViewsToAppLayout(appLayout, appIcon, appName)
            appsLayouts += appItem

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

        for (elem in 0 until appsLayouts.size) {
            val animator = viewAnimator.constructAnimation(
                view = appsLayouts[elem],
                left = animationPattern[elem]["left"]!!,
                top = animationPattern[elem]["top"]!!,
                right = animationPattern[elem]["right"]!!,
                bottom = animationPattern[elem]["bottom"]!!,
                startAngle = animationPattern[elem]["startAngle"]!!,
                sweepAngle = animationPattern[elem]["sweepAngle"]!!
            )

            viewAnimator.startAnimation(
                animator,
                20000L,
                22
            )

        }
    }


    fun listenNotifications() {
       val b = NotificationService()
    }





}



