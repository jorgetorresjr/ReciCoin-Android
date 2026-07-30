package com.example.recicoin.model

enum class MaterialType(
    val displayName: String,
    val coinsPerKg: Int
) {
    PAPER(
        "Papel",
        3
    ),
    PLASTIC(
        "Plástico",
        5
    ),
    GLASS(
        "Vidro",
        4
    ),
    METAL(
        "Metal",
        10
    ),
    ELECTRONICS(
        "Eletrônicos",
        20
    )
}