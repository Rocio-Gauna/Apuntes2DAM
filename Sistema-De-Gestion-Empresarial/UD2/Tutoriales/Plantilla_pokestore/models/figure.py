from odoo import fields, models
class PokeStoreFigure(models.Model):
 
   _name = "pokestore.figure"
   _description = "Figura coleccionable"
   _order = "name"
   name = fields.Char(string="Nombre", required=True)
   pokemon = fields.Char(string="Pokémon", required=True)
   collection = fields.Char(string="Colección")
   rarity = fields.Selection(
      [
         ("normal", "Normal"),
         ("rare", "Rara"),
         ("exclusive", "Exclusiva"),
         ],
         string="Rareza",
         default="normal",
         required=True,
         )
price = fields.Float(string="Precio", required=True)
stock = fields.Integer(string="Stock", default=0)
active = fields.Boolean(default=True)
