const viewPath = ('products');
const Product = require('../models/product');
const products = require('../routes/products');

exports.index = async (req, res) => {};

exports.show = async (req, res) => {
  const product = await Product.findById(req.params.id);
  res.render(`${viewPath}/show`, {
    pageTitle: product.name,
    product: product
  });
};

exports.new = (req, res) => {
  res.render(`${viewPath}/new`, {
    pageTitle: 'New Product'
  });
};

exports.create = async (req, res) => {

try{
  const product = await Product.create(req.body);
  res.redirect(`/products/${product.id}`);
} catch (err) {
  res.send(err);
}
};

exports.edit = async (req, res) => {};

exports.update = async (req, res) => {};

exports.delete = async (req, res) => {};