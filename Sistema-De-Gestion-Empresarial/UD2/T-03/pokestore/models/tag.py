# Importamos las herramientas de Odoo
from odoo import fields, models


# Clase nueva: el molde de las etiquetas (Preventa, Edición limitada, Caja dañada...)
class PokeStoreTag(models.Model):
    # Nombre técnico del modelo
    _name = "pokestore.tag"
    # Nombre en texto normal
    _description = "Etiqueta de figura"
    # Ordenar por nombre
    _order = "name"

    # Nombre de la etiqueta: obligatorio, así no hay etiquetas vacías
    name = fields.Char(string="Nombre", required=True)