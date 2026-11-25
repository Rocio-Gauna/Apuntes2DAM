# api: contiene los decoradores (@api.depends, @api.constrains, @api.onchange)
# fields y models: para crear campos y modelos, igual que antes
from odoo import api, fields, models
# ValidationError: el mensaje de error rojo que ve el usuario cuando algo está mal
from odoo.exceptions import ValidationError


# Mismo molde de figura de los tutoriales anteriores
class PokeStoreFigure(models.Model):
    # Nombre técnico del modelo
    _name = "pokestore.figure"
    # Nombre en texto normal
    _description = "Figura coleccionable"
    # Orden alfabético por nombre
    _order = "name"

    # Nombre de la figura (obligatorio)
    name = fields.Char(string="Nombre", required=True)
    # Pokémon que representa (obligatorio)
    pokemon = fields.Char(string="Pokémon", required=True)
    # Many2one: la colección a la que pertenece (del Tutorial 2)
    collection_id = fields.Many2one("pokestore.collection", string="Colección")
    # Many2many: etiquetas de la figura (del Tutorial 2)
    tag_ids = fields.Many2many("pokestore.tag", string="Etiquetas")
    # Lista desplegable de rareza
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
    # Precio con decimales (obligatorio)
    price = fields.Float(string="Precio", required=True)
    # Unidades disponibles (entero, empieza en 0)
    stock = fields.Integer(string="Stock", default=0)
    # Casilla para archivar sin borrar
    active = fields.Boolean(default=True)
    # Text = texto largo de varias líneas (para las notas internas)
    internal_notes = fields.Text(string="Notas internas")
    # Campo calculado: no lo escribe el usuario, lo calcula Odoo
    # compute="_compute_stock_value" = "para calcularme, ejecuta ese método"
    # store=True = guarda el resultado en la base de datos (así se puede buscar y ordenar)
    stock_value = fields.Float(string="Valor del stock", compute="_compute_stock_value", store=True)

    # Decorador: "vuelve a calcular cuando cambie price o stock"
    @api.depends("price", "stock")
    # Método que calcula el valor; el nombre coincide con el del compute de arriba
    def _compute_stock_value(self):
        # Recorremos cada figura del grupo, una a una
        for record in self:
            # El valor del stock es precio por unidades: 10 de precio y 3 de stock = 30
            record.stock_value = record.price * record.stock

    # Decorador: "comprueba estas reglas cuando se guarde price, stock, rarity o collection_id"
    @api.constrains("price", "stock", "rarity", "collection_id")
    # Método que revisa las reglas de negocio
    def _check_figure_rules(self):
        # Revisamos las figuras una por una
        for record in self:
            # Regla 1: el precio no puede ser negativo
            if record.price < 0:
                # Lanza el error rojo; el guardado se cancela y no se guarda nada
                raise ValidationError("El precio no puede ser negativo.")
            # Regla 2: el stock no puede ser negativo
            if record.stock < 0:
                # Mismo mecanismo con otro mensaje
                raise ValidationError("El stock no puede ser negativo.")
            # Regla 3: una figura exclusiva necesita colección (collection_id vacío = "no hay colección")
            if record.rarity == "exclusive" and not record.collection_id:
                # Mensaje que explica qué corregir
                raise ValidationError("Una figura exclusiva debe tener una colección asignada.")

    # Decorador: "cuando el usuario cambie rarity en el formulario, ejecuta este método"
    @api.onchange("rarity")
    # Método de ayuda del formulario (no protege datos, solo ayuda)
    def _onchange_rarity(self):
        # En un onchange también se recorre self por costumbre
        for record in self:
            # Solo si eligió Exclusiva Y las notas están vacías (así no pisamos lo que escribió)
            if record.rarity == "exclusive" and not record.internal_notes:
                # Escribimos una recomendación; el usuario puede cambiarla antes de guardar
                record.internal_notes = "Embalar en caja rígida con protección extra y sello de exclusiva."

    # Método del botón "Agotar stock": se llama desde el formulario con type="object"
    def action_deplete_stock(self):
        # self puede traer una o varias figuras, así que recorremos todas
        for record in self:
            # Ponemos el stock a 0; al cambiar stock, Odoo recalcula solo stock_value (por el @api.depends)
            record.stock = 0