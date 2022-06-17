package com.existentio.branchedlauncher.ui.leadscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.existentio.branchedlauncher.databinding.FragmentLeadBinding
import com.existentio.branchedlauncher.model.App
import com.existentio.branchedlauncher.ui.animation.patterns.AnimationPattern
import com.existentio.branchedlauncher.ui.animation.ClockwiseViewAnimator
import com.existentio.branchedlauncher.ui.animation.ViewAnimator
import com.existentio.branchedlauncher.ui.widgets.AppView
import com.existentio.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_ICON_HEIGHT_MEDIUM
import com.existentio.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_ICON_WIDTH_MEDIUM
import com.existentio.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_LAYOUT_HEIGHT
import com.existentio.branchedlauncher.ui.widgets.AppView.AppViewConstants.APP_LAYOUT_WIDTH
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LeadScreenFragment : Fragment() {

    private val notificationId: Int = 11
    private val CHANNEL_ID: String = "100"
    private var _binding: FragmentLeadBinding? = null
    private val viewModel: LeadScreenViewModel by viewModels()
    private val binding get() = _binding!!

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

        val appsLayouts = attachNonStaticAppViewContainer()
        val clockwiseWiseAnimator = ClockwiseViewAnimator()

        val navController = findNavController()
        val touchListener = LeadScreenSwipeListener(requireContext(), navController)
        binding.leadLayout.setOnTouchListener(touchListener)

        startNonStaticAppViewAnimation(appsLayouts, clockwiseWiseAnimator)
        attachStaticAppViewContainer()
        attachAppsFolder()

        //temporal implementation for debugging
//        createNotificationChannel()
//        createNotification()
//        createNotification2()
//        createNotification3()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadApps(): MutableList<App> = viewModel.loadApps()

    private fun loadRandomApps(): MutableList<App> = viewModel.loadRandomApps()

    private fun attachNonStaticAppViewContainer(): List<LinearLayout> {
        val appView = AppView(requireContext())
        val apps = loadRandomApps()
        val appsLayouts = mutableListOf<LinearLayout>()

        for (x in 0 until apps.size) {
            val appLayout = appView.createAppLayout()
            binding.leadLayout.addView(appLayout)
            val appName = appView.createAppName(apps[x].name)
            val appIcon = appView.createAppIcon(apps[x].icon)

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
            appsLayouts[x].gravity = Gravity.CENTER

            appIcon.setOnClickListener {
                Log.d("onTouched", apps[x].name)
                val intent = appName.context.packageManager.getLaunchIntentForPackage(
                    apps[x].packageName
                )
                this.startActivity(intent)
            }

        }

        return appsLayouts
    }

    private fun attachStaticAppViewContainer() {
        val apps = loadApps().take(4)
        binding.ivStaticApp1.setImageDrawable(apps[0].icon)
        binding.ivStaticApp2.setImageDrawable(apps[1].icon)
        binding.ivStaticApp3.setImageDrawable(apps[2].icon)
        binding.ivStaticApp4.setImageDrawable(apps[3].icon)

        for (x in 0 until binding.mtcvStaticAppsContainer.childCount) {
            binding.mtcvStaticAppsContainer.getChildAt(x).setOnClickListener {
                val intent = context?.packageManager?.getLaunchIntentForPackage(
                    apps[x].packageName
                )
                this.startActivity(intent)
            }


        }
    }

    private fun attachAppsFolder() {
        binding.ivFolder.setOnClickListener {
            binding.mtcvFolderContent.visibility = View.VISIBLE
            onResume()
        }
    }

    private fun startNonStaticAppViewAnimation(
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
                animator, 20000L, -1
            )
        }
    }


//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            val notificationManager: NotificationManager =
//                requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun createNotification() {
//        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_pest_control_rodent_violet_24)
//            .setContentTitle("Title")
//            .setContentText("Notification great text")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(requireContext())) {
//            notify(notificationId, builder.build())
//        }
//
//    }
//
//    private fun createNotification2() {
//        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_pest_control_rodent_violet_24)
//            .setContentTitle("Title 2")
//            .setContentText("Notification great text 2")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//            with(NotificationManagerCompat.from(requireContext())) {
//                notify(12, builder.build())
//        }
//
//    }
//
//    private fun createNotification3() {
//        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_pest_control_rodent_violet_24)
//            .setContentTitle("Title 3")
//            .setContentText("Notification great text 3")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(requireContext())) {
//            notify(13, builder.build())
//        }
//
//    }


}



