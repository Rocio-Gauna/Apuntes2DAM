# Importamos dos herramientas de Odoo: fields (para crear columnas) y models (para crear modelos)
from odoo import fields, models


# Creamos una clase (un molde). Hereda de models.Model, que es lo que la convierte en tabla de Odoo
class PokeStoreFigure(models.Model):
    # Nombre técnico del modelo: es el nombre de la tabla en la base de datos (con punto, no con guion bajo)
    _name = "pokestore.figure"
    # Nombre en texto normal, para mensajes y para saber qué es esto
    _description = "Figura coleccionable"
    # Al listar figuras, ordénalas por el campo name (alfabético)
    _order = "name"

    # Campo de texto corto, obligatorio (required=True). string es la etiqueta que ve el usuario
    name = fields.Char(string="Nombre", required=True)
    # Otro texto corto obligatorio: qué Pokémon es la figura
    pokemon = fields.Char(string="Pokémon", required=True)
    # Texto corto NO obligatorio: nombre de la colección (más adelante se cambia por una relación)
    collection = fields.Char(string="Colección")
    # Selection = lista desplegable con opciones fijas
    rarity = fields.Selection(
        [
            # Cada opción es (valor_guardado_en_base_de_datos, texto_que_ve_el_usuario)
            ("normal", "Normal"),
            ("rare", "Rara"),
            ("exclusive", "Exclusiva"),
        ],
        # Etiqueta del campo en pantalla
        string="Rareza",
        # Si el usuario no elige nada, se pone "normal" automáticamente
        default="normal",
        # No se puede guardar sin rareza
        required=True,
    )
    # Float = número con decimales (por ejemplo 19.99); es obligatorio
    price = fields.Float(string="Precio", required=True)
    # Integer = número entero (3 figuras, no 3.5); empieza en 0
    stock = fields.Integer(string="Stock", default=0)
    # Boolean = casilla de sí/no. El campo se llama active porque Odoo lo trata de forma especial
    # (los registros con active=False se esconden en las listas, como una papelera)
    active = fields.Boolean(default=True)