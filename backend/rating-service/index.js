const connection = require('./database')

var moment = require('moment');

exports.addRating = function addRating(req, res) {
	console.log(req.body.value)
    let value = req.body.value;
	let approved = req.body.approved;
	let posting_date = moment().format("YYYY-MM-DD HH:mm:ss");
	let comment = req.body.comment;
	let accommodation_id = req.body.accommodation_id;
	let reservator = req.body.reservator;
    console.log(posting_date);
	connection.query("insert into rating (value, approved, posting_date, comment, accommodation_id, reservator) values (?,?,?,?,?,?)",[value, approved, posting_date, comment, accommodation_id, reservator], (err, result) => {
		if (err) { console.log(err); res.status(400).send(err); }
		else {
			res.status(200).send('added');
		}
	});
};
exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from rating", (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getAllUnapprovedRatings = function getAllUnapprovedRatings(req, res) {
    connection.query("select * from rating where approved = 0", (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getAURatings = function getAURatings(req, res) {
    connection.query("select * from rating where accommodation_id="+req.query.id, (err, result) => {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getAUApprovedRatings = function getAUApprovedRatings(req, res) {
    connection.query("select * from rating where approved = 1 and accommodation_id="+req.query.id, (err, result) => {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};

exports.getRatingAverage = function getRatingAverage(req, res) {
	connection.query("select avg(value) as ratingAvg, count(value) as no_ratings from rating where accommodation_id="+req.query.id, (err, result) => {
		if (err) res.status(400).send(err);
		else {
			res.status(200).send(result);
		}
	});
};