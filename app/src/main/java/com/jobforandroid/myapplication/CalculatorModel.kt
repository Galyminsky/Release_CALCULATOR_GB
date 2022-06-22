package com.jobforandroid.myapplication

class CalculatorModel {
    private var firstArg = 0
    private var secondArg = 0
    private val inputStr = StringBuilder()
    private var actionSelected = 0
    private var state: State

    private enum class State {
        firstArgInput, operationSelected, secondArgInput, resultShow
    }

    fun onNumPressed(buttonId: Int) {
        if (state == State.resultShow) {
            state = State.firstArgInput
            inputStr.setLength(0)
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput
            inputStr.setLength(0)
        }
        if (inputStr.length < 9) {
            when (buttonId) {
                R.id.zero -> if (inputStr.length != 0) {
                    inputStr.append("0")
                }
                R.id.one -> inputStr.append("1")
                R.id.two -> inputStr.append("2")
                R.id.three -> inputStr.append("3")
                R.id.four -> inputStr.append("4")
                R.id.five -> inputStr.append("5")
                R.id.six -> inputStr.append("6")
                R.id.seven -> inputStr.append("7")
                R.id.eight -> inputStr.append("8")
                R.id.nine -> inputStr.append("9")
            }
        }
    }

    fun onActionPressed(actionId: Int) {
        if (actionId == R.id.equals && state == State.secondArgInput && inputStr.length > 0) {
            secondArg = inputStr.toString().toInt()
            state = State.resultShow
            inputStr.setLength(0)
            when (actionSelected) {
                R.id.plus -> inputStr.append(firstArg + secondArg)
                R.id.minus -> inputStr.append(firstArg - secondArg)
                R.id.multiply -> inputStr.append(firstArg * secondArg)
                R.id.division -> inputStr.append(firstArg / secondArg)
            }
        } else if (inputStr.length > 0 && state == State.firstArgInput && actionId != R.id.equals) {
            firstArg = inputStr.toString().toInt()
            state = State.operationSelected
            actionSelected = actionId
        }
    }

    val text: String
        get() {
            val str = StringBuilder()
            return when (state) {
                State.operationSelected -> str.append(
                    firstArg
                ).append(' ')
                    .append(operationChar)
                    .toString()
                State.secondArgInput -> str.append(firstArg)
                    .append(' ')
                    .append(operationChar)
                    .append(' ')
                    .append(inputStr)
                    .toString()
                State.resultShow -> str.append(firstArg)
                    .append(' ')
                    .append(operationChar)
                    .append(' ')
                    .append(secondArg)
                    .append(" = ")
                    .append(inputStr.toString())
                    .toString()
                else -> inputStr.toString()
            }
        }
    private val operationChar: Char
        private get() = when (actionSelected) {
            R.id.plus -> '+'
            R.id.minus -> '-'
            R.id.multiply -> '*'
            R.id.division -> '/'
            else -> '/'
        }

    fun reset() {
        state = State.firstArgInput
        inputStr.setLength(0)
    }

    init {
        state = State.firstArgInput
    }
}