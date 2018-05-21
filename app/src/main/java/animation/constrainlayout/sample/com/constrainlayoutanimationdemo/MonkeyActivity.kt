package animation.constrainlayout.sample.com.constrainlayoutanimationdemo

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_monkey_detail.*

class MonkeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monkey_detail)
        configureAnimation()
    }

    private fun configureAnimation() {
        var endConstraintSetApplied = false

        val transition = ChangeBounds()
        transition.interpolator = FastOutSlowInInterpolator()

        val startConstraintSet = ConstraintSet()
        startConstraintSet.clone(constraintLayout)

        val endConstraintSet = ConstraintSet()
        endConstraintSet.clone(this, R.layout.activity_monkey)

        constraintLayout.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout, transition)
            val constraint = if (endConstraintSetApplied) startConstraintSet else endConstraintSet
            constraint.applyTo(constraintLayout)
            endConstraintSetApplied = !endConstraintSetApplied
        }
    }
}