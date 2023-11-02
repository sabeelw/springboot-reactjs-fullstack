
import { UsersContainer } from './container-component.style';

export const SampleContainer = (props) => {
    return (
        <UsersContainer maxWidth="md" props={props}>{props.children}</UsersContainer>
    );
}
