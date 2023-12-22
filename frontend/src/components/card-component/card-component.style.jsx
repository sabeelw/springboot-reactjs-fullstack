
import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { styled } from '@mui/material';
import CardMedia from '@mui/material/CardMedia';



export const SampleCard = (props) => {
    const { id, name, email, age } = props.user;
    return (
        <Card sx={{ width: "fit-content" }}>

            <CardContent sx={{ display: 'flex', flexDirection: 'row', justifyContent: "center", gap: "1rem" }}>
                <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                    {Object.entries(props.user).map((v, i) => {
                        const [key, val] = v
                        return (

                            <Typography color="text.secondary" key={i}>
                                {key}: {val}
                            </Typography>
                        )
                    })}
                </Box>

                <CardMedia
                    component="img"
                    sx={{ width: 151 }}
                    image="https://mui.com/static/images/cards/live-from-space.jpg"
                    alt="Live from space album cover"
                />
            </CardContent>


        </Card>
    );
}
export const UserCard = styled(SampleCard)({
    padding: 8,
    margin: 8,
    borderRadius: "10px"
})