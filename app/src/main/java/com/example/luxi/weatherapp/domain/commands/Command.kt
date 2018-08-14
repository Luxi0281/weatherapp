package com.example.luxi.weatherapp.domain.commands

public interface Command <out T> {
    fun execute(): T
}