package com.example.lunchtray.datasource

import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem

/**
 * Carte végétarienne simplifiée avec des plats savoureux et frais.
 */
object DataSource {

    val entreeMenuItems = listOf(
        EntreeItem(
            name = "Chou-Fleur Rôti Epicé",
            description = "Chou-fleur rôti, mariné et frit, avec des épices maison.",
            price = 8.00,
        ),
        EntreeItem(
            name = "Chili des Trois Haricots Mystiques",
            description = "Haricots noirs, rouges et kidney, mijotés avec des épices et des oignons.",
            price = 5.50,
        ),
        EntreeItem(
            name = "Pâtes Sauvages aux Champignons",
            description = "Penne, champignons, basilic, ail et huile d'olive.",
            price = 6.00,
        ),
        EntreeItem(
            name = "Skillet Haricots Noirs Piquants",
            description = "Haricots noirs épicés, légumes de saison, avocat et oignons marinés.",
            price = 7.00,
        )
    )

    val sideDishMenuItems = listOf(
        SideDishItem(
            name = "Salade d’Été Ensoleillée",
            description = "Tomates, pêches, avocat et vinaigrette balsamique.",
            price = 3.00,
        ),
        SideDishItem(
            name = "Soupe de Courge Rôtie et Poivrons",
            description = "Soupe de courge musquée et poivrons grillés, légèrement épicée.",
            price = 3.50,
        ),
        SideDishItem(
            name = "Pommes de Terre Epices Vives",
            description = "Pommes de terre épicées, rôties et frites.",
            price = 2.50,
        ),
        SideDishItem(
            name = "Riz Noix de Coco & Citron Vert",
            description = "Riz parfumé à la noix de coco et au citron vert.",
            price = 2.00,
        )
    )

    val accompanimentMenuItems = listOf(
        AccompanimentItem(
            name = "Pain Maison du Midi",
            description = "Pain frais au levain, doré et croustillant.",
            price = 1.00,
        ),
        AccompanimentItem(
            name = "Mélange de Baies Fraîches",
            description = "Fraîches fraises, bleuets et framboises.",
            price = 1.50,
        ),
        AccompanimentItem(
            name = "Légumes Marinés Maison",
            description = "Concombres et carottes marinés maison.",
            price = 1.00,
        )
    )
}
