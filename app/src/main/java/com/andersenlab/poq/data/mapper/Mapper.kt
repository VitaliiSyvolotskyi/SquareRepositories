package com.andersenlab.poq.data.mapper

interface Mapper<in MODEL_A, out MODEL_B> {

    /*
    * @returns mapped value
    * @param model which is mapping to MODEL_B
    */
    fun mapModel(model: MODEL_A): MODEL_B
}